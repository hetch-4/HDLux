package models;

// Represents a customer in the hotel booking system
public class Customer {
    private int customerId; // Unique identifier for the customer
    private String name; // Full name of the customer
    private String email; // Email address of the customer
    private String phoneNumber; // Phone number of the customer

    // Constructor to initialize customer details
    public Customer(int customerId, String name, String email, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getter for customer ID
    public int getCustomerId() {
        return customerId;
    }

    // Setter for customer ID
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // Getter for customer name
    public String getName() {
        return name;
    }

    // Setter for customer name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for customer email
    public String getEmail() {
        return email;
    }

    // Setter for customer email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for customer phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setter for customer phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Displays the customer information in a readable format
    @Override
    public String toString() {
        return "Customer:{" +
                "\nID : " + customerId +
                "\nName : " + name +
                "\nEmail : " + email +
                "\nPhone number : " + phoneNumber +
                "\n}";
    }
}