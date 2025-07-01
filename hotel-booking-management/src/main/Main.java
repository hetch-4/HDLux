import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        BookingService bookingService = new BookingService();
        RoomService roomService = new RoomService();
        System.out.println("Booking Service");

        Customer customer = new Customer(12, "James", "james@gmail.com", "+254716453281");
        Room room = new Room("Family", 101, 1);

        System.out.println("Enter Checkin Date");
        String checkinInput = scanner.nextLine();
        LocalDate checkinDate = LocalDate.parse(checkinInput, formatter);
        room.setCheckin(checkinDate);

        System.out.println("Enter Checkout Date");
        String checkoutInput = scanner.nextLine();
        LocalDate checkoutDate = LocalDate.parse(checkoutInput, formatter);
        room.setCheckout(checkoutDate);

        
        Booking booking = bookingService.createBooking(customer, room, checkinDate, checkoutDate);
        System.out.println("New booking created\n");
        System.out.println("Customer: " + customer + "\nRoom: " + room + "\nBooking: " + booking);

        scanner.close();
    }
}

class Booking {
    private int bookingId;
    private Customer customer;
    private Room room;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;

    // Constructor
    public Booking(int bookingId, Customer customer, Room room, LocalDate checkinDate, LocalDate checkoutDate) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.room = room;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @Override
    public String toString(){
        return "Booking {"+
            "\nBooking Id : "+bookingId+
            "\nCustomer : " +customer+
            "\nRoom : "+room+
            "\nCheck In Date : "+checkinDate+
            "\nCheck Out Date : "+checkoutDate+
            "\n}";
    }
}


class Customer {
    private int customerId;
    private String name;
    private String email;
    private String phoneNumber;

    public Customer(int customerId, String name, String email, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString(){
        return "Customer:{"+
        "\nID : "+customerId+
        "\nName : "+name+
        "\nemail : "+email+
        "\nPhone number : "+phoneNumber+
        "\n}";
    }
}


class Room {

    private String roomType;
    private int roomId;
    private int floor;
    private LocalDate checkin;
    private LocalDate checkout;

    public Room(String roomType, int roomId, int floor) {
        this.roomType = roomType;
        this.roomId = roomId;
        this.floor = floor;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getFloor() {
        return floor;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "Room {" +
                "\nRoom Type: " + roomType +
                "\nRoom Id: " + roomId +
                "\nFloor: " + floor +
                "\nCheckin: " + checkin +
                "\nCheckout: " + checkout +
                "\n}";
    }

    public boolean isAvailable(LocalDate startDate, LocalDate endDate) {
        if (checkin == null || checkout == null) {
            return true; // Room is available if no check-in or check-out dates are set
        }
        return endDate.isBefore(checkin) || startDate.isAfter(checkout);
    }
}



class BookingService {
    private Map<Integer, Booking> bookingStorage = new HashMap<>();

    // Method to create a booking
    public Booking createBooking(Customer customer, Room room, LocalDate checkinDate, LocalDate checkoutDate) {
        int bookingId = bookingStorage.size() + 1; // Generate a unique booking ID
        Booking booking = new Booking(bookingId, customer, room, checkinDate, checkoutDate);
        bookingStorage.put(bookingId, booking);
        return booking;
    }

    // Method to cancel a booking
    public boolean cancelBooking(int bookingId) {
        if (bookingStorage.containsKey(bookingId)) {
            bookingStorage.remove(bookingId);
            System.out.println("Cancellation successful");
            return true;
        } else {
            System.out.println("Booking ID not found");
            return false;
        }
    }

    // Method to retrieve a booking
    public Booking retrieveBooking(int bookingId) {
        if (bookingStorage.containsKey(bookingId)) {
            return bookingStorage.get(bookingId);
        } else {
            System.out.println("Booking does not exist");
            return null;
        }
    }
}



class RoomService {
    ArrayList<Room> roomList = new ArrayList<>();
    // Method to add a room
    public void addRoom(Room room) {
        // Implementation for adding a room
        // Assuming there's a list to store rooms
        if (room != null) {
            roomList.add(room);
            System.out.println("Room added successfully: " + room);
        } else {
            System.out.println("Invalid room. Cannot add.");
        }
    }

    // Method to remove a room
    public void removeRoom(int roomId) {
        // Implementation for removing a room
        Room roomToRemove = null;
        for (Room room : roomList) {
            if (room.getRoomId() == roomId) {
                roomToRemove = room;
                break;
            }
        }
        if (roomToRemove != null) {
            roomList.remove(roomToRemove);
            System.out.println("Room removed successfully: " + roomToRemove);
        } else {
            System.out.println("Room with ID " + roomId + " not found.");
        }
    }

    // Method to list available rooms
    public ArrayList<Room> listAvailableRooms() {
        // Implementation for listing available rooms
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : roomList) {
            if (room.isAvailable(room.getCheckin(), room.getCheckout())) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
}