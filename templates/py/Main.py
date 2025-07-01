import sqlite3
from datetime import datetime, timedelta

class HotelBookingSystem:
    def __init__(self, db_name='hotel_booking.db'):
        self.db_name = db_name
        self._initialize_database()
    
    def _initialize_database(self):
        """Initialize the database with required tables"""
        with sqlite3.connect(self.db_name) as conn:
            cursor = conn.cursor()
            
            # Create rooms table
            cursor.execute('''
                CREATE TABLE IF NOT EXISTS rooms (
                    room_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    room_number TEXT UNIQUE NOT NULL,
                    room_type TEXT NOT NULL,
                    price_per_night REAL NOT NULL,
                    capacity INTEGER NOT NULL,
                    amenities TEXT,
                    is_available BOOLEAN DEFAULT TRUE
                )
            ''')
            
            # Create guests table
            cursor.execute('''
                CREATE TABLE IF NOT EXISTS guests (
                    guest_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    first_name TEXT NOT NULL,
                    last_name TEXT NOT NULL,
                    email TEXT UNIQUE NOT NULL,
                    phone TEXT,
                    address TEXT
                )
            ''')
            
            # Create bookings table
            cursor.execute('''
                CREATE TABLE IF NOT EXISTS bookings (
                    booking_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    guest_id INTEGER NOT NULL,
                    room_id INTEGER NOT NULL,
                    check_in_date DATE NOT NULL,
                    check_out_date DATE NOT NULL,
                    total_price REAL NOT NULL,
                    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    status TEXT DEFAULT 'confirmed',
                    FOREIGN KEY (guest_id) REFERENCES guests (guest_id),
                    FOREIGN KEY (room_id) REFERENCES rooms (room_id)
                )
            ''')
            
            conn.commit()
    
    def add_room(self, room_number, room_type, price_per_night, capacity, amenities=None):
        """Add a new room to the system"""
        with sqlite3.connect(self.db_name) as conn:
            cursor = conn.cursor()
            cursor.execute('''
                INSERT INTO rooms (room_number, room_type, price_per_night, capacity, amenities)
                VALUES (?, ?, ?, ?, ?)
            ''', (room_number, room_type, price_per_night, capacity, amenities))
            conn.commit()
            return cursor.lastrowid
    
    def add_guest(self, first_name, last_name, email, phone=None, address=None):
        """Add a new guest to the system"""
        with sqlite3.connect(self.db_name) as conn:
            cursor = conn.cursor()
            cursor.execute('''
                INSERT INTO guests (first_name, last_name, email, phone, address)
                VALUES (?, ?, ?, ?, ?)
            ''', (first_name, last_name, email, phone, address))
            conn.commit()
            return cursor.lastrowid
    
    def make_booking(self, guest_id, room_id, check_in_date, check_out_date):
        """Create a new booking"""
        # Calculate total price
        room_price = self.get_room_price(room_id)
        nights = (datetime.strptime(check_out_date, '%Y-%m-%d') - 
                 datetime.strptime(check_in_date, '%Y-%m-%d')).days
        total_price = room_price * nights
        
        # Check room availability
        if not self.is_room_available(room_id, check_in_date, check_out_date):
            return None
        
        with sqlite3.connect(self.db_name) as conn:
            cursor = conn.cursor()
            cursor.execute('''
                INSERT INTO bookings (guest_id, room_id, check_in_date, check_out_date, total_price)
                VALUES (?, ?, ?, ?, ?)
            ''', (guest_id, room_id, check_in_date, check_out_date, total_price))
            
            # Mark room as unavailable if booking is confirmed
            cursor.execute('''
                UPDATE rooms SET is_available = FALSE WHERE room_id = ?
            ''', (room_id,))
            
            conn.commit()
            return cursor.lastrowid
    
    def cancel_booking(self, booking_id):
        """Cancel an existing booking"""
        with sqlite3.connect(self.db_name) as conn:
            cursor = conn.cursor()
            
            # Get room_id from booking
            cursor.execute('SELECT room_id FROM bookings WHERE booking_id = ?', (booking_id,))
            room_id = cursor.fetchone()[0]
            
            # Update booking status
            cursor.execute('''
                UPDATE bookings SET status = 'cancelled' WHERE booking_id = ?
            ''', (booking_id,))
            
            # Mark room as available
            cursor.execute('''
                UPDATE rooms SET is_available = TRUE WHERE room_id = ?
            ''', (room_id,))
            
            conn.commit()
            return True
    
    def get_room_price(self, room_id):
        """Get price per night for a room"""
        with sqlite3.connect(self.db_name) as conn:
            cursor = conn.cursor()
            cursor.execute('SELECT price_per_night FROM rooms WHERE room_id = ?', (room_id,))
            result = cursor.fetchone()
            return result[0] if result else None
    
    def is_room_available(self, room_id, check_in_date, check_out_date):
        """Check if a room is available for given dates"""
        with sqlite3.connect(self.db_name) as conn:
            cursor = conn.cursor()
            
            # Check if room is marked as available
            cursor.execute('''
                SELECT is_available FROM rooms WHERE room_id = ?
            ''', (room_id,))
            room_available = cursor.fetchone()[0]
            if not room_available:
                return False
            
            # Check for overlapping bookings
            cursor.execute('''
                SELECT COUNT(*) FROM bookings 
                WHERE room_id = ? 
                AND status = 'confirmed'
                AND (
                    (check_in_date <= ? AND check_out_date > ?) OR
                    (check_in_date < ? AND check_out_date >= ?) OR
                    (check_in_date >= ? AND check_out_date <= ?)
                )
            ''', (room_id, check_in_date, check_in_date, check_out_date, check_out_date, 
                  check_in_date, check_out_date))
            
            overlapping_bookings = cursor.fetchone()[0]
            return overlapping_bookings == 0
    
    def get_available_rooms(self, check_in_date, check_out_date, room_type=None):
        """Get all available rooms for given dates and optional room type"""
        with sqlite3.connect(self.db_name) as conn:
            cursor = conn.cursor()
            
            query = '''
                SELECT r.* FROM rooms r
                WHERE r.is_available = TRUE
                AND r.room_id NOT IN (
                    SELECT b.room_id FROM bookings b
                    WHERE b.status = 'confirmed'
                    AND (
                        (b.check_in_date <= ? AND b.check_out_date > ?) OR
                        (b.check_in_date < ? AND b.check_out_date >= ?) OR
                        (b.check_in_date >= ? AND b.check_out_date <= ?)
                    )
                )
            '''
            params = (check_in_date, check_in_date, check_out_date, check_out_date, 
                     check_in_date, check_out_date)
            
            if room_type:
                query += ' AND r.room_type = ?'
                params += (room_type,)
            
            cursor.execute(query, params)
            return cursor.fetchall()
    
    def get_guest_bookings(self, guest_id):
        """Get all bookings for a specific guest"""
        with sqlite3.connect(self.db_name) as conn:
            cursor = conn.cursor()
            cursor.execute('''
                SELECT b.*, r.room_number, r.room_type 
                FROM bookings b
                JOIN rooms r ON b.room_id = r.room_id
                WHERE b.guest_id = ?
                ORDER BY b.check_in_date
            ''', (guest_id,))
            return cursor.fetchall()
    
    def get_room_details(self, room_id):
        """Get details for a specific room"""
        with sqlite3.connect(self.db_name) as conn:
            cursor = conn.cursor()
            cursor.execute('SELECT * FROM rooms WHERE room_id = ?', (room_id,))
            return cursor.fetchone()
    
    def get_guest_details(self, guest_id):
        """Get details for a specific guest"""
        with sqlite3.connect(self.db_name) as conn:
            cursor = conn.cursor()
            cursor.execute('SELECT * FROM guests WHERE guest_id = ?', (guest_id,))
            return cursor.fetchone()


# Example usage
if __name__ == "__main__":
    hotel = HotelBookingSystem()
    
    # Add some rooms
    hotel.add_room("101", "Standard", 100.00, 2, "TV, WiFi")
    hotel.add_room("102", "Standard", 100.00, 2, "TV, WiFi")
    hotel.add_room("201", "Deluxe", 150.00, 2, "TV, WiFi, Mini-fridge")
    hotel.add_room("301", "Suite", 250.00, 4, "TV, WiFi, Mini-fridge, Jacuzzi")
    
    # Add a guest
    guest_id = hotel.add_guest("John", "Doe", "john.doe@example.com", "555-1234")
    
    # Check available rooms
    check_in = "2023-12-15"
    check_out = "2023-12-20"
    available_rooms = hotel.get_available_rooms(check_in, check_out)
    print("Available rooms:")
    for room in available_rooms:
        print(f"Room {room[1]} - {room[2]} - ${room[3]}/night")
    
    # Make a booking
    if available_rooms:
        room_id = available_rooms[0][0]
        booking_id = hotel.make_booking(guest_id, room_id, check_in, check_out)
        print(f"Booking confirmed! Booking ID: {booking_id}")
    
    # View guest bookings
    bookings = hotel.get_guest_bookings(guest_id)
    print("\nGuest bookings:")
    for booking in bookings:
        print(f"Booking {booking[0]}: Room {booking[8]} ({booking[9]}) - {booking[3]} to {booking[4]}")
    
    # Cancel a booking
    if bookings:
        hotel.cancel_booking(bookings[0][0])
        print("Booking cancelled")