package models;


import java.time.LocalDate;


public class Booking {
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

