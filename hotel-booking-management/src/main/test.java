import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;


public class Main{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Enter Checkin date (yyyy-MM-dd)");
        String cinInput = scanner.nextLine();
        LocalDate checkin = LocalDate.parse(cinInput, formatter);

        System.out.println("Enter Checkout date (yyyy-MM-dd)");
        String coutInput = scanner.nextLine();
        LocalDate checkout = LocalDate.parse(coutInput, formatter);

        Booking booking = new Booking(checkin, checkout);
        System.out.println("Booking :"+booking);
        scanner.close();
    }
}

public class Booking{
    LocalDate checkin, checkout;    
    long daysOfStay;

    //class constructor
    public Booking(LocalDate checkin, LocalDate checkout){
        this.checkin = checkin;
        //logic to check if check out date is before check in date
        if(checkout.isBefore(checkin)){
            System.out.println("Error! checkout should be after checkout");
        }else{
            this.checkout = checkout;
            this.daysOfStay = ChronoUnit.DAYS.between(checkin, checkout);
        }
    }

    //setters
    public String toString(){
        return "Checkin Date: "+checkin+" Checkout Date:"+checkout+" Days of stay:"+daysOfStay;
    }

}