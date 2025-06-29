package models;

import java.time.LocalDate;
import java.time.format.*;

// Represents a room in the hotel
public class Room {
    private String roomType; // Type of the room (e.g., Single, Double, Suite)
    private int roomId; // Unique identifier for the room
    private int floor; // Floor number where the room is located

    // Dates for room booking
    private LocalDate checkin; // Check-in date for the room
    private LocalDate checkout; // Check-out date for the room

    private double price; // Price per night for the room
    private int capacity; // Maximum number of occupants for the room
    private boolean available; // Indicates if the room is currently available

    // Constructor to initialize room details
    public Room(String roomType, int roomId, int floor) {
        this.roomType = roomType;
        this.roomId = roomId;
        this.floor = floor;
    }

    // Getters for room properties
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

    public LocalDate getCheckout() {
        return checkout;
    }

    // Setters for room booking dates
    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    // Displays the room information in a readable format
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

    // Checks if the room is available for a given date range
    public boolean isAvailable(LocalDate startDate, LocalDate endDate) {
        if (checkin == null || checkout == null) {
            return true; // Room is available if no check-in or check-out dates are set
        }
        // Room is available if the requested date range does not overlap with existing bookings
        return endDate.isBefore(checkin) || startDate.isAfter(checkout);
    }
}