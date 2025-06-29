

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import utils.DateUtils;

import models.*;
import services.*;

public class App {

    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System");
        //initialize customer class //create customer
        Customer customer = new Customer(1, "John Doe", "john.doe@example.com", "1234567890");
        //initialize Room class // create room
        Room room = new Room("Deluxe",101, 200);
        //declare check in and checkout dates
        LocalDate checkinDate = LocalDate.parse("2023-10-01");
        LocalDate checkoutDate = LocalDate.parse("2023-10-05");
        //initialize booking //create booking
        Booking booking = new Booking(1, customer, room, checkinDate, checkoutDate);

        // Print initialized data
        System.out.println("Sample Customer: " + customer);
        System.out.println("Sample Room: " + room);
        System.out.println("Sample Booking: " + booking);
        // For example, you could set up services and start the user interface
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Booking Service");

        Customer customer12 = new Customer(12, "James", "james@gmail.com", "+254716453281");
        Room room12 = new Room("Family", 101, 1);

        System.out.println("Enter Checkin Date");
        String checkinInput = scanner.nextLine();
        LocalDate checkinDate1 = LocalDate.parse(checkinInput, formatter);
        room.setCheckin(checkinDate);

        System.out.println("Enter Checkout Date");
        String checkoutInput = scanner.nextLine();
        LocalDate checkoutDate1 = LocalDate.parse(checkoutInput, formatter);
        room.setCheckout(checkoutDate);

        BookingService bookingService = new BookingService();
        Booking booking12 = bookingService.createBooking(customer12, room, checkinDate1, checkoutDate1);
        System.out.println("New booking created\n");
        System.out.println("Customer: " + customer + "\nRoom: " + room + "\nBooking: " + booking);

        scanner.close();
    }
}