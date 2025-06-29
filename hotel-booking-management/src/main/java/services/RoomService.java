package services;
import models.Room;

import java.time.LocalDate;
import java.util.ArrayList;


public class RoomService {
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