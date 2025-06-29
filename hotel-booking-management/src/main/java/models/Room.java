package models;

import java.time.LocalDate;
import java.time.format.*;;

public class Room {
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