package services;

import models.Customer;
import models.Room;
import models.Booking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BookingServiceMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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

        BookingService bookingService = new BookingService();
        Booking booking = bookingService.createBooking(customer, room, checkinDate, checkoutDate);
        System.out.println("New booking created\n");
        System.out.println("Customer: " + customer + "\nRoom: " + room + "\nBooking: " + booking);

        scanner.close();
    }
}

public class BookingService {
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

    @Override
    public void searchRoom(LocalDate checkin){
        //search room according to checkin and checkout dates
         
    }

    @Override 
    public void searchRoom(String roomType){
        //search room according to room type
    }

    @Override
    public void searchRoom(int floor){
        //Search room according to the floor
    }

    @Override
    public void searchRoom(double price){
        //searchroom according to price
    }

    @Override
    public void searchRoom(boolean available){
        //search room according to available rooms
    }
}