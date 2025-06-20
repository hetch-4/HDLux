import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Home Room Resation Form");
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       
        System.out.println("Enter Name ");
        String name = scanner.nextLine();
       
        System.out.println("Enter check in date (yyyy-MM-dd):");
        String ciInput = scanner.nextLine();

        System.out.println("Enter check out date (yyyy-MM-dd):");
        String coInput = scanner.nextLine();

        System.out.println("Enter email");
        String emailInput = scanner.nextLine();

        System.out.println("Enter phone");
        String phoneInput = scanner.nextLine();

        System.out.println("Enter number of guests");
        int capacityInput  = scanner.nextInt();

            LocalDate checkin = LocalDate.parse(ciInput, formatter);
            LocalDate checkout = LocalDate.parse(coInput, formatter);
        


        User user = new User(name, checkin, checkout);
        user.setEmail(emailInput);
        user.setPhone(phoneInput);
        user.setCapacity(capacityInput);

        System.out.println("User Created :-"+ user);
    }
}

public class User{
    private String name;
    private String phone, email;
    private LocalDate checkin;
    private LocalDate checkout;
    private int capacity;
    private int roomType;
    //constructor
    public User(String name, LocalDate Checkin, LocalDate checkout){
        this.name = name;
        this.checkin = Checkin;
        this.checkout = checkin;
    }

    //getters 
    public LocalDate getCheckin(){
        return checkin;
    }
    public LocalDate getCheckout(){
        return checkout;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
    //setters 
    public void setName(String name,String lname){
        String setname = name + lname;
        this.name = setname;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setCapacity(int capacity){
        if(capacity>0){
            this.capacity = capacity;
        }else{
            System.out.println("Number of guests should be greater than 0");
        }
    }

    //Display info
    //@Override

    //to String Method
    //@Override
    public String toString(){
        return "User{\n"+
        " Name='"+name+'\''+"\n"+
        " CheckIn="+checkin +'\''+"\n"+
        " Checkout="+checkout+'\''+"\n"+
        " Phone="+phone+'\''+"\n"+
        " Email="+email+'\''+"\n"+
        " Capacity="+capacity+'\''+"\n"+
        "}";
    }

    
}