import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


// Base Class
abstract class Vehicle{

    String make,model;
    int year,rentalRate;
    boolean isAvailable;
    String mechanism;       // auto/manual
    String Catagory;
    boolean haveAC;

    public Vehicle(){
        // default
    }


    public Vehicle(String make,String model,int year, int rentalRate,boolean isAvailable,String mechanism, boolean haveAC, String Catagory){
        this.make = make;
        this.model = model;
        this.year = year;
        this.rentalRate = rentalRate;
        this.isAvailable = isAvailable;
        this.mechanism = mechanism;
        this.haveAC = haveAC;
        this.Catagory = Catagory;
    }
    
    Scanner sc = new Scanner(System.in);
    Customer c = new Customer();
    abstract public void bookACar(Admin admin);

}



// Inherited class => EconomyCar

class EconomyCar extends Vehicle{

    String bookedCarName;
    boolean carFound = false;
    int days;

    EconomyCar(){
        // default constructor
    }

    
    EconomyCar(String make , String model , int year , int rentalRate, boolean isAvailable , String mechanism, boolean haveAC, String Catagory){
        super(make, model, year, rentalRate, isAvailable, mechanism, haveAC , Catagory);
    }


    public void bookACar(Admin admin){

        System.out.print("\nEnter the name of car you want to take ? ");
        bookedCarName = sc.nextLine();
        
        for (EconomyCar e : admin.eCar){

            if(e.model.equalsIgnoreCase(bookedCarName)){

                System.out.println(e.model + " found !!");
                System.out.print("For how many days you need a car ?");
                days = sc.nextInt();
                System.out.println( "\n< Booking Summary >" + "\nCar Name : " + e.model + "\nBooked for " + days + " Days" + "\nTotal Rental cost : "  + days*e.rentalRate);
                System.out.println("Thanks for Booking a Car!!");
                System.out.println("");
                carFound = true;
                e.isAvailable = false;
                break;
            }
            
        }

        if(!carFound){
            System.out.println("Car not Found.. Try to write correct name of car!!");
        }

    }


}


// Inherited class => LuxuryCar

class LuxuryCar extends Vehicle{


    String bookedCarName;
    boolean carFound = false;
    int days;

    LuxuryCar(){

    }


    LuxuryCar(String make , String model , int year , int rentalRate, boolean isAvailable , String mechanism, boolean haveAC, String Catagory){
        super(make, model, year, rentalRate, isAvailable, mechanism, haveAC , Catagory);
    }



    public void bookACar(Admin admin){

        System.out.print("\nEnter the name of car you want to take ? ");
        bookedCarName = sc.nextLine();
        
        for (LuxuryCar e : admin.lCar){

            if(e.model.equalsIgnoreCase(bookedCarName)){

                System.out.println(e.model + " found !!");
                System.out.print("For how many days you need a car ?");
                days = sc.nextInt();
                System.out.println( "\n< Booking Summary >" + "\nCar Name : " + e.model + "\nBooked for " + days + " Days" + "\nTotal Rental cost : "  + days*e.rentalRate);
                System.out.println("Thanks for Booking a Car!!");
                System.out.println("");
                carFound = true;
                e.isAvailable = false;
                break;
            }
            
        }

        if(!carFound){
            System.out.println("Car not Found.. Try to write correct name of car!!");
        }


    }


}


// Inherited class => FancyCar

class FancyCar extends Vehicle{
    

    String bookedCarName;
    boolean carFound = false;
    int days;


    FancyCar(){

    }

    FancyCar(String make , String model , int year , int rentalRate, boolean isAvailable , String mechanism, boolean haveAC, String Catagory){
        super(make, model, year, rentalRate, isAvailable, mechanism, haveAC , Catagory);
    }



    public void bookACar(Admin admin){


        System.out.print("\nEnter the name of car you want to take ? ");
        bookedCarName = sc.nextLine();
        
        for (FancyCar e : admin.fCar){

            if(e.model.equalsIgnoreCase(bookedCarName)){

                System.out.println(e.model + " found !!");
                System.out.print("For how many days you need a car ?");
                days = sc.nextInt();
                System.out.println( "\n< Booking Summary >" + "\nCar Name : " + e.model + "\nBooked for " + days + " Days" + "\nTotal Rental cost : "  + days*e.rentalRate);
                System.out.println("Thanks for Booking a Car!!");
                System.out.println("");
                carFound = true;
                e.isAvailable = false;
                break;
            }
            
        }

        if(!carFound){
            System.out.println("Car not Found.. Try to write correct name of car!!");
        }


    }

    public void rentalHistory(){
        // logic for rental history
    }

}


class Customer{


    private String name;
    private char haveLicense;
    private long phoneNo;
    private String LicenseExp;
    private boolean permitVerified = false;
    Scanner sc = new Scanner(System.in);
    LocalDate today = LocalDate.now();      //get present date

    public void getDetails(){


        System.out.print("Enter your name :");
        name = sc.nextLine();
        
        System.out.print("Enter your phone number :");
        phoneNo = sc.nextLong();

        sc.nextLine();
        System.out.print("Do you have driving permit? (y/n)");
        haveLicense = sc.nextLine().charAt(0);

        if(haveLicense == 'y'){

            // License validator

            System.out.print("Provide your License expiry date (dd-MM-yyyy): ");
            String licenseExp = sc.nextLine(); // taking date input
    
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
            try {

                LocalDate formattedDate = LocalDate.parse(licenseExp, formatter);
    
                if (formattedDate.isBefore(today)) {
                    System.out.println("Sorry user!! Your license has expired.");
                } else {
                    System.out.println("Permit Verified :)");
                    System.out.println("Registered Successfully!! You can proceed now.");
                    permitVerified = true;
                }
    
            } catch (DateTimeParseException e) {
                System.out.println("Date format is invalid: " + e.getMessage());
            }

        }
        else if(haveLicense == 'n'){
            System.out.println("Sorry! You can't get car on rent.");
            System.out.println("Login Failed :( ");
        }
        else{
            System.out.println("Invalid Selection!! ");
        }

    }   


    public boolean permitVerified(){
        return permitVerified;
    }


}



// Admin panel 

class Admin {


    boolean carFound = false;
    
    ArrayList<EconomyCar> eCar = new ArrayList<>();
    ArrayList<LuxuryCar> lCar = new ArrayList<>();
    ArrayList<FancyCar> fCar = new ArrayList<>();


    public void addEconomyCar(EconomyCar eCar){
        this.eCar.add(eCar);
    }
    public void addLuxuryCar(LuxuryCar lCar){
        this.lCar.add(lCar);
    }
    public void addFancyCar(FancyCar fCar){
        this.fCar.add(fCar);
    }

    public void economyCars(){

        for (EconomyCar e : eCar){
            System.out.println("Car Specifications: ");
            System.out.println("------------------------- ");
            System.out.println("Catagory : " + e.Catagory);
            System.out.println("Make : " + e.make);
            System.out.println("Model : " + e.model);
            System.out.println("Year : " + e.year);
            System.out.println("Rental Rate (per day) : " + e.rentalRate);
            System.out.println("Availability : " + e.isAvailable);
            System.out.println("Mechanism : " + e.mechanism);
            System.out.println("Have AC : " + e.haveAC);
            System.out.println();
        }

    }

    
    public void luxuryCars(){

        for (LuxuryCar l : lCar){
            System.out.println("Car Specifications: ");
            System.out.println("------------------------- ");
            System.out.println("Catagory : " + l.Catagory);
            System.out.println("Make : " + l.make);
            System.out.println("Model : " + l.model);
            System.out.println("Year : " + l.year);
            System.out.println("Rental Rate (per day) : " + l.rentalRate);
            System.out.println("Availability : " + l.isAvailable);
            System.out.println("Mechanism : " + l.mechanism);
            System.out.println("Have AC : " + l.haveAC);
            System.out.println();
            
        }

    }

    
    public void fancyCars(){

        for (FancyCar f : fCar){
            System.out.println("Car Specifications: ");
            System.out.println("------------------------- ");
            System.out.println("Catagory : " + f.Catagory);
            System.out.println("Make : " + f.make);
            System.out.println("Model : " + f.model);
            System.out.println("Year : " + f.year);
            System.out.println("Rental Rate (per day) : " + f.rentalRate);
            System.out.println("Availability : " + f.isAvailable);
            System.out.println("Mechanism : " + f.mechanism);
            System.out.println("Have AC : " + f.haveAC);
            System.out.println();
        
        }
    }


    public void removeCar(String catagory, String carName){


        if(catagory.equalsIgnoreCase("economy")){
            for (EconomyCar e : eCar) {
                if(e.model.equalsIgnoreCase(carName)){
                    eCar.remove(e);
                    System.out.println(carName + " removed Successfully !!");
                    carFound = true;
                }
            }
            if(!carFound){
                System.out.println("Car not Found.. Try to write correct name of car!!");
            }    
        }
        else if(catagory.equalsIgnoreCase("luxury")){
            for (LuxuryCar l : lCar) {
                if(l.model.equalsIgnoreCase(carName)){
                    lCar.remove(l);
                    System.out.println(carName + " removed Successfully !!");
                    carFound = true;
                }
            }
            if(!carFound){
                System.out.println("Car not Found.. Try to write correct name of car!!");
            }    
        }
        else if(catagory.equalsIgnoreCase("fancy")){
            for (FancyCar f : fCar) {
                if(f.model.equalsIgnoreCase(carName)){
                    fCar.remove(f);
                    System.out.println(carName + " removed Successfully !!");
                    carFound = true;
                }
            }
            if(!carFound){
                System.out.println("Car not Found.. Try to write correct name of car!!");
            }    
        }
        else{
            System.out.println("Invalid Catagory!!");
        }


    }


}


// main function
public class main {


    public static void main(String[] args) {
    
  
        Admin admin = new Admin();
        Customer c = new Customer();
        Scanner sc = new Scanner(System.in);
        EconomyCar economyCar = new EconomyCar();
        LuxuryCar luxuryCar = new LuxuryCar();
        FancyCar fancyCar = new FancyCar();
        
        String userName = "admin";
        String passcode = "2024";
        String enteredUserName,enteredPasscode;
        char option,menuChoice,carType;
        boolean haveRegistered = false;

        // APP HEADER

        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("                             ______     ___  _____                     ___   ___        ___ ");
        System.out.println("                             |          |       |     |   *   | |    | |     |     |    |    ");
        System.out.println("                             |          |___    |     |  * *  | |___ | |___  |___  |    |___  "); 
        System.out.println("                             |    __    |       |     | *   * | |    | |     |     |        | "); 
        System.out.println("                             |_____ |   |___    |     |*     *| |    | |___  |___  |___  ___| "); 
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                   BOOK YOUR RIDE FROM US ");
        

         // Auto Added cars
        admin.addEconomyCar(new EconomyCar("Suzuki", "Alto VXR", 2023, 5000, true, "Manual", true, "Economy"));
        admin.addEconomyCar(new EconomyCar("Suzuki", "Cutlus VXL", 2023,7000, true, "Automatic", true, "Economy"));
        admin.addLuxuryCar(new LuxuryCar("Honda", "Civic Oriel", 2023,12000, true, "Automatic", true, "Luxury"));
        admin.addLuxuryCar(new LuxuryCar("Toyota", "Corolla XLI", 2021,15000, true, "Automatic", true, "Luxury"));
        admin.addFancyCar(new FancyCar("Toyota", "Fortuner 2.8", 2021,30000, true, "Automatic", true, "Fancy Car"));
        admin.addFancyCar(new FancyCar("Mercedes Benz", "E Class", 2023,50000, true, "Automatic", true, "Fancy Car"));
         


        do{


        System.out.println("1. Admin Panel");
        System.out.println("2. User Panel");
        System.out.println("3. Exit");

        System.out.print("Your choice : ");
        int fchoice = sc.nextInt();


        // Show menu for admin Panel

        if(fchoice == 1){


            String make, model, mechanism, catagory;
            int year, rentalRate;
            boolean isAvailable,haveAC;


            sc.nextLine();
            System.out.print("Enter your admin username: ");
            enteredUserName = sc.nextLine();

            System.out.print("\nEnter your admin passcode: ");
            enteredPasscode = sc.nextLine();

            if(enteredPasscode.equals(passcode) && enteredUserName.equals(userName)){
                
                System.out.println("\n1. Add a car to garage");
                System.out.println("2. Remove a car from garage");
                System.out.println("3. Show my garage");
                System.out.println("4. Go Back");
                System.out.print("Your choice: ");
                int choice = sc.nextInt();

                switch (choice) {


                    case 1:
                        //add car

                        sc.nextLine();
                        System.out.print("Enter the make of car : ");
                        make = sc.nextLine();
                        System.out.print("Enter the of model : ");
                        model = sc.nextLine();
                        System.out.print("Enter the year : ");
                        year = sc.nextInt();
                        System.out.print("Enter the rental rate : ");
                        rentalRate = sc.nextInt();
                        System.out.print("Car Have AC (0 = false / 1 = true): ");
                        haveAC = sc.nextBoolean();
                        sc.nextLine();
                        System.out.print("Mechanism of car (Auto/Manual): ");
                        mechanism = sc.nextLine();
                        System.out.print("Set Availability (0 = false / 1 = true) : ");
                        isAvailable = sc.nextBoolean();
                        sc.nextLine();
                        System.out.print("In which catagory you want to add this car (economy/luxury/fancy)?");
                        catagory = sc.nextLine();

                        if(catagory.equalsIgnoreCase("Economy")){
                            admin.addEconomyCar(new EconomyCar(make, model, year, rentalRate, isAvailable, mechanism, haveAC, catagory));  
                        }
                        else if(catagory.equalsIgnoreCase("Luxury")){
                            admin.addLuxuryCar(new LuxuryCar(make, model, year, rentalRate, isAvailable, mechanism, haveAC, catagory));  
                        }
                        else if(catagory.equalsIgnoreCase("Fancy")){
                            admin.addFancyCar(new FancyCar(make, model, year, rentalRate, isAvailable, mechanism, haveAC, catagory));  
                        }
                        else{
                            System.out.println("Invalid Catagory!!");
                        }

                    break;
                
                    case 2:

                        //remove car

                        System.out.println();
                        System.out.println(" --------------------------");
                        System.out.println("|        Our Garage        |");
                        System.out.println(" --------------------------");
                        System.out.println();
                        admin.economyCars();
                        admin.luxuryCars();
                        admin.fancyCars();

                        // Logic for removal of any car
                        sc.nextLine();
                        System.out.print("Enter the Catagory of Car  :");
                        String CarCatagory = sc.nextLine();

                        System.out.print("Enter the name of car to remove :");
                        String carName = sc.nextLine();

                        admin.removeCar(CarCatagory, carName);


                    break;

                    case 3:
                        //show garage
                        
                        System.out.println();
                        System.out.println(" --------------------------");
                        System.out.println("|        Our Garage        |");
                        System.out.println(" --------------------------");
                        System.out.println();
                        admin.economyCars();
                        admin.luxuryCars();
                        admin.fancyCars();

                    break;

                    case 4:
                        //go back
                    break;

                    default:
                    System.out.println("Invalid Entry!!");
                    break;

                }

            }
            else{
                System.out.println("Wrong Credentional!!!");
            }

        }


        // Show menu for user Panel

        else if(fchoice == 2){


            do{
              

            System.out.println("\n1. Register yourself");
            System.out.println("2. Show Fleet");
            System.out.println("3. Book a Car");
            System.out.println("4. Help");
            System.out.print("Your choice: ");
            int choice = sc.nextInt();

            switch (choice) {


                case 1:
                    //regsiter yourself

                    if(!haveRegistered){   
                        c.getDetails();
                        haveRegistered = c.permitVerified();    // this check for user registration      
                    }else{
                        System.out.println("You have already regsitered yourself!!");
                    }
                
                break;
            
                case 2:

                    // Show all cars in garage

                    System.out.println();
                    System.out.println(" --------------------------");
                    System.out.println("|        Our Fleet         |");
                    System.out.println(" --------------------------");
                    System.out.println();
                    admin.economyCars();
                    admin.luxuryCars();
                    admin.fancyCars();
                

                break;

                case 3:

                    // Book a specific car

                    if(haveRegistered){

                        System.out.println();
                        System.out.println("Type of cars in garage: ");
                        
                        System.out.println("a. Economy");
                        System.out.println("b. Luxury");
                        System.out.println("c. Fancy");
                        sc.nextLine();
                        System.out.print("Select Your Type : ");
                        carType = sc.nextLine().charAt(0);

                        System.out.println();

                        if(carType == 'a'){           
                            admin.economyCars(); // Show economy cars
                            economyCar.bookACar(admin);
                        }

                        else if(carType == 'b'){
                            admin.luxuryCars(); // Show fancy cars
                            luxuryCar.bookACar(admin);
                        }

                        else if(carType == 'c'){
                            admin.fancyCars(); // Show fancy cars
                            fancyCar.bookACar(admin);
                        }   
                    
                        else{
                            System.out.println("Invalid Entry!!");
                        }
                    
                    }   
                    else{
                        System.out.println("Dear User!! You have to login first.");
                    }

                break;

                case 5:

                    // add some faqs for help

                    System.out.println("Q1: What is a car rental management system?");
                    System.out.println("Q2: How does the car rental management system work?");
                    System.out.println("Q3: Who can use this system?");
                    System.out.println("Which question would you like to be answered : ");
                    int choiceAns = sc.nextInt();
                    if(choiceAns == 1){
                        System.out.println("A car rental management system is designed to help car rental companies manage their fleet of vehicles, process bookings, track rentals, and handle customer interactions efficiently.");
                    }else if(choiceAns == 2){
                        System.out.println("It allows Admin to add a car to garage , remove a car from garage and see his fleet . ");
                        System.out.println("It allows user to book a car and user must have a driving permit.");
                    }else if (choiceAns == 3){
                        System.out.println("This system is designed for car rental businesses of all sizes, including small local agencies and large international companies. It can also be used by individuals managing personal car rental operations.");
                    }else{
                        System.out.println("Illegal Entry.");
                    }

                break;

                default:
                System.out.println("Please Select the correct option!!");
                break;

            } 
            
            sc.nextLine();
            System.out.println("Want to Stay in user Panel? ");
            menuChoice = sc.nextLine().charAt(0);
          
            }while (menuChoice == 'y' || menuChoice == 'Y');
            
        }
        else if(fchoice == 3){
            // exit on option 3 
            break;
        }      
        else{
            System.out.println("Invalid Entry Please try again!!");
        }

            sc.nextLine();
            System.out.print("Do want to exit from app? ");
            option = sc.nextLine().charAt(0);


        }while(option == 'n' || option == 'N');


        sc.close();

    }


}
