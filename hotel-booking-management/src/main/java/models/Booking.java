package models;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Booking {
    private int bookingId;
    private Customer customer;
    private Room room;
    private long daysOfStay;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;

    // Constructor
    public Booking(int bookingId, Customer customer, Room room, LocalDate checkinDate, LocalDate checkoutDate) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.room = room;
        this.checkinDate = checkinDate;

        //logic to check if checkout date is after checkin
        if(checkoutDate.isBefore(checkinDate)){
            throw new IllegalArgumentException("Check-out date cannot be before check-in date");
        }else{
            this.checkoutDate =checkoutDate;
            this.daysOfStay = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
        }
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
        if (checkoutDate.isBefore(checkinDate)) {
            throw new IllegalArgumentException("Check-out date cannot be before check-in date");
        }else{
            this.checkoutDate = checkoutDate;
            this.daysOfStay = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
            
        }
    }

    @Override
    public String toString(){
        return "Booking {"+
            "\nBooking Id : "+bookingId+
            "\nCustomer : " +customer+
            "\nRoom : "+room+
            "\nCheck In Date : "+checkinDate+
            "\nCheck Out Date : "+checkoutDate+
            "\nDays of Stay : "+daysOfStay+
            "\n}";
    }

   
}

