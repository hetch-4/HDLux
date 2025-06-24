import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Main{
    public static void main(String [] args){
        System.out.println("Room Management");
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("Enter Room Type :['Non AC','Family','Deluxe']");
        String roomTypeInput = scanner.nextLine();

        System.out.println("Enter Room Id");
        int roomIdInput = scanner.nextInt();

        System.out.println("Enter Floor");
        int floorInput = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Checkin date (yyyy-MM-dd)");
        String checkinIput = scanner.nextLine();

        System.out.println("Enter checkout date");
        String checkoutInput = scanner.nextLine();

        LocalDate checkin = LocalDate.parse(checkinIput, formatter);
        LocalDate checkout = LocalDate.parse(checkoutInput, formatter);

        if(!checkin.isBefore(checkout)){
            System.out.println("Error! Checkin should be before checkout");
            return ;
        }

        Room room = new Room(roomTypeInput,roomIdInput,floorInput);
        room.setCheckin(checkin);
        room.setCheckout(checkout);
        System.out.println("Room Created: "+room);
    }
}

class Room{
    private String roomType;
    private int roomId;
    private int floor;
    private LocalDate checkin;
    private LocalDate checkout;

    //constructor
    public Room(String roomType, int roomId, int floor){
        this.roomId = roomId;
        this.roomType = roomType;
        this.floor = floor;
    }

    //setters 
    public void setCheckin(LocalDate checkin){
        this.checkin = checkin;
    }
    public void setCheckout(LocalDate checkout){
        this.checkout = checkout;
    }

    //getters
    public String getRoomType(){
        return roomType;
    }
    public int getRoomId(){
        return roomId;
    }
    public int getFloor(){
        return floor;
    }
    public LocalDate getCheckin(){
        return checkin;
    }
    public LocalDate getCheckout(){
        return checkout;
    }

    public String toString(){
        return "Room {"+
        "\nRoom Type: "+ roomType +
        "\nRoom Id: "+ roomId +
        "\nFloor: "+ floor+
        "\nCheckin: "+checkin+
        "\nCheckout: "+checkout+
        "\n}";
    }

}

class Hotel{
    
}