import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User{
    private String username;
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password=password;
    }

    public String Username(){
        return username;
    }

    public String Password(){
        return password;
    }
}

class Reservation{
    private int TrainNumber;
    private String TrainName;
    private String Pnr;
    private String Class;
    private String JourneyDate;
    private String From;
    private String To;

public Reservation(int TrainNumber,String TrainName,String Pnr,String Class,String JourneyDate,String From,String To){
    this.TrainNumber=TrainNumber;
    this.TrainName= TrainName;
    this.Pnr=Pnr;
    this.Class=Class;
    this.JourneyDate=JourneyDate;
    this.From=From;
    this.To=To;
}

public int getTrainNumber(){
    return TrainNumber;
}
public String getTrainName(){
    return TrainName;
}
public String getPnr(){
    return Pnr;
}
public String getClassType(){
    return Class;
}
public String getJourneyDate(){
    return JourneyDate;
}
public String getFrom(){
    return From;
}
public String getTo(){
    return To;
}
}

class ReservationSystem{
    private Map<String,User>users;
    private ArrayList<Reservation>reservations;
    private Scanner scanner;

    public ReservationSystem(){
        this.users = new HashMap<>();
        this.reservations=new ArrayList<>();
        this.scanner=new Scanner(System.in);
    }

    public void run(){
        System.out.println("***Welcome To Online Reservation System***");
        users.put("Subash",new User("Subash","2907"));
        User currentUser = login();
        if (currentUser!=null){
            System.out.println("Login Successfully!");

            while(true){
                System.out.println("\nMain Menu:");
                System.out.println("1.Reservation");
                System.out.println("2.Cancel Reservation");
                System.out.println("3.Logout");

                int choice=getIntInput("Enter Your Choice:");
                switch(choice){
                    case 1:
                    makeReservation(currentUser);
                    break;
                    case 2:
                    cancelReservation();
                    break;
                    case 3:
                    System.out.println("Logout Successfully!");
                    return;
                    default:
                    System.out.println("Invalid Choice - Try Again");
                }
            }
        }else{
            System.out.println("Login Failed.Incorrect Username or Password");
        }
    }

    private User login(){
        System.out.println("Enter UserName :");
        String username = scanner.nextLine();
        System.out.println("Enter Password :");
        String password=scanner.nextLine();

        return users.getOrDefault(username,null);
    }

    private void makeReservation(User user){
        System.out.println("\nReservation Form::");
        System.out.println("Enter Train Number :");
        int TrainNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Train Name :");
        String TrainName = scanner.nextLine();
        String Pnr = generatePnr();
        System.out.println("Enter Class :");
        String Class = scanner.nextLine();
        System.out.println("Enter Date of Journey :");
        String JourneyDate = scanner.nextLine();
        System.out.println("Enter Boarding Place :");
        String From=scanner.nextLine();
        System.out.println("Enter Destination Place :");
        String To = scanner.nextLine();

        Reservation reservation = new Reservation(TrainNumber,TrainName,Pnr,Class,JourneyDate,From,To);
        reservations.add(reservation);

        System.out.println("Reservation Done Successfull!!!");
        displayReservationDetails(reservation);
    }

    private void cancelReservation(){
        System.out.println("\nCancellation Form:");
        System.out.println("Enter PNR Number :");
        String Pnr = scanner.nextLine();
        scanner.nextLine();

        Reservation reservation = findReservationByPNR(Pnr);
        if(reservation!=null){
            displayReservationDetails(reservation);
            System.out.println("Do You Want To Confirm cancellation?(y/n):");
            String confirmation=scanner.nextLine().toLowerCase();
            if(confirmation.equals("y")){
                reservations.remove(reservation);
                System.out.println("Cancellation Successfull!!");
            }else{
                System.out.println("Cancellation Aborted!!");
            }
        }else{
            System.out.println("Reservation Not Found For PNR:"+Pnr);
        }
    }

    private void displayReservationDetails(Reservation reservation){
        System.out.println("Reservation Details::");
        System.out.println("Train Number :"+reservation.getTrainNumber());
        System.out.println("Train Name :"+reservation.getTrainName());
        System.out.println("PNR :"+reservation.getPnr());
        System.out.println("Class :"+reservation.getClassType());
        System.out.println("Date of Journey :"+reservation.getJourneyDate());
        System.out.println("Boarding Place :"+reservation.getFrom());
        System.out.println("Destination Place :"+reservation.getTo());
    }

    private String generatePnr(){
        return "PNR"+System.currentTimeMillis();
    }

    private int getIntInput(String prompt){
        System.out.println(prompt);
        while(!scanner.hasNextInt()){
            System.out.println("Invalid Input-Please Enter Number");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private Reservation findReservationByPNR(String Pnr){
        for (Reservation reservation:reservations){
            if (reservation.getPnr().equals(Pnr))
            {
                return reservation;
            }
        }
        return null;
    }
}

public class OnlineReservationSystem{
    public static void main(String[] args){
        ReservationSystem reservationSystem = new ReservationSystem();
        reservationSystem.run();
    }
}