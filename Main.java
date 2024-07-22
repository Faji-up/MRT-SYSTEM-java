import java.util.Scanner;

public class PROJECT{
    


    // 2D array representing the fare between each pair of MRT stations
    public static int locations[][] = { 
            { 0, 13, 13, 16, 16, 20, 20, 20, 24, 24, 24, 28, 28 },
            { 13, 0, 13, 13, 16, 16, 20, 20, 20, 24, 24, 24, 28 },
            { 13, 13, 0, 13, 13, 16, 16, 20, 20, 20, 24, 24, 24 },
            { 16, 13, 13, 0, 13, 13, 16, 16, 20, 20, 20, 24, 24 },
            { 16, 16, 13, 13, 0, 13, 13, 16, 16, 20, 20, 20, 24 },
            { 20, 16, 16, 13, 13, 0, 13, 13, 16, 16, 20, 20, 20 },
            { 20, 20, 16, 16, 13, 13, 0, 13, 13, 16, 16, 20, 20 },
            { 20, 20, 20, 16, 16, 13, 13, 0, 13, 13, 16, 16, 20 },
            { 24, 20, 20, 20, 16, 16, 13, 13, 0, 13, 13, 16, 16 },
            { 24, 24, 20, 20, 20, 16, 16, 13, 13, 0, 13, 13, 16 },
            { 24, 24, 24, 20, 20, 20, 16, 16, 13, 13, 0, 13, 13 },
            { 28, 24, 24, 24, 20, 20, 20, 16, 16, 13, 13, 0, 13 },
            { 28, 28, 24, 24, 24, 20, 20, 20, 16, 16, 13, 13, 0 } };

    // Array containing the names of MRT stations
    public static String locations_list[] = { 
    		"North Avenue", 
    		"Quezon Avenue", 
    		"GMA", 
    		"Cubao", 
    		"Santolan", 
    		"Ortigas",
                "Shaw Blvd.", 
                "Boni", 
                "Guadalupe", 
                "Buendia", 
                "Ayala", 
                "Magallanes", 
                "Taft" 
                };
            

    // Variables for payment, single journey amount, card balance, change, current and destination stations, and user choice
    public static int payment;
    public static int single;
    public static int card = 100; // Initial balance for beep card
    public static double change;
    public static int current;
    public static int destination;
    public static int choice;
    
    // Boolean flag for controlling the iteration in the main loop
    public static boolean iterator = true;
    
    // Discount value for senior citizens
    public static double discount_val = 0;
    
    // Scanner object for user input
    public static Scanner scanner = new Scanner(System.in);

    // Main method
    public static void main(String[] args) {

        // Array of abbreviated station names for display
        String achro[] = { "NA ", "QA ", "GMA ", "Cub ", "Stln", "Ort ", "SB. ", "Bon ", "Gua ", "Bue ", "Aya ", "Mg ",
                "Taf " };
        int itr = 0;

        // Display the fare matrix
        System.out.println(" NA QA GMA Cub Stln Ort SB Bon Gua Bue Ayl Mag Taft".toUpperCase());
        for (int x[] : locations) {
            System.out.print(achro[itr].toUpperCase());
            for (int y : x) {
                System.out.printf("%4d", y);
            }
            itr += 1;
            System.out.println();
        }
        System.out.println();
        System.out.println();

        // Display MRT header
        System.out.println("METRO RAIL TRANSIT LINE 3 (MRT 3)");

        System.out.println();

        // Main loop for MRT operations
        do {
            mrt();
        } while (iterator);
    }

    // Method for handling single journey transactions
    public static void single_journey() {
        System.out.println();
        System.out.println("Enter Amount :");
        System.out.print(">> ");
        single = scanner.nextInt();

        System.out.println();
        System.out.println("Enter age");
        System.out.print(">> ");
        int age = scanner.nextInt();
        // Check if the passenger is eligible for a discount
        if (check_age(age)) {
            double discount = 0.2 * payment;
            discount_val = discount;
            payment = payment - (int) discount;
        }
        // Process the transaction
        if (single > payment) {
            change = single - payment;
            single -= payment;
        } else {
            System.out.println("Amount Invalid!");
            System.out.println("Try again");
            mrt();
        }
    }

    // Method for handling beep card transactions
    public static void use_beep_card() {
        if (card > 0 && card != 0) {
            System.out.println();
            System.out.println("Enter age");
            System.out.print(">> ");
            int age = scanner.nextInt();
            // Check if the passenger is eligible for a discount
            if (check_age(age)) {
                double discount = 0.2 * payment;
                discount_val = discount;
                payment = payment - (int) discount;
            }
            // Process the transaction
            change = card - payment;
            card -= payment;
        } else {
            System.out.println("Insufficient Balance");
            System.out.println("Try again");
            add_balance();
        }
    }

    // Method for checking and processing the fare
    public static void check(int current, int destination) {
        if (current != destination) {
            payment = locations[current][destination];
            if (payment < card) {
                if (choice == 0) {
                    single_journey();
                } else if (choice == 1) {
                    use_beep_card();
                } else {
                    System.out.println("Invalid Choice!");
                    System.out.println("Try again");
                    mrt();
                }
            } else {
                System.out.println("Insufficient Balance");
                System.out.println("Try again");
                add_balance();
            }


        } else {
            System.out.println("Invalid Choice!");
            System.out.println("Try again");
            mrt();
        }
    }

    // Main method for MRT operations
    public static void mrt() {
        System.out.println();
        int id = 0;

        // Display station options
        for (String loc : locations_list) {
            System.out.printf("[%d] [%s]", id, loc);
            System.out.println();
            id += 1;
        }
        System.out.println();
        System.out.println("==========================================================================");
        System.out.println();
        System.out.println("Select your current location:");
        System.out.print(">>> ");
        current = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Select your Destination:");
        System.out.print(">>> ");
        destination = scanner.nextInt();
        System.out.println();

        System.out.println("==========================================================================");
        System.out.println();
        System.out.printf("%s\n%s\n", "[0]Single journey", "[1]Beep Card");
        System.out.println();
        System.out.print(">>> ");
        choice = scanner.nextInt();

        check(current, destination);
        System.out.println("==========================================================================");
        System.out.println();
        System.out.println("Waiting Area [" + locations_list[current] + "]");
        System.out.println("Please Wait for the train to arrive");

        System.out.println();
        System.out.println("==========================================================================");
        System.out.println();

        System.out.println("Current location :" + locations_list[current]);
        System.out.println("Destination : " + locations_list[destination]);
        System.out.println();
        System.out.println("Fare Price : " + payment);
        System.out.println("Your Discount : " + discount_val);

        if (choice == 1) {
            System.out.println("Beep Card balance : " + card);
        } else if (choice == 0) {
            System.out.println("Change Amount : " + change);
        }
        System.out.println();
        System.out.println("==========================================================================");

        System.out.println();
        System.out.println("Another journey? [0]Yes [1]exit");
        System.out.print(">> ");
        choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 0) {
            System.out.println();
            discount_val = 0;
        } else if (choice == 1) {
            iterator = false;
            System.exit(1);
           
        }
    }

    // Method to check if the passenger is a senior citizen
    public static boolean check_age(int age) {
        // check if the age is greater than 60 and then it will be discounted of 20% 
        if(age >= 60) {
            return true; 
        } else {
            return false;
        }
    }
    
    public static void add_balance(){
     System.out.println(); System.out.println("==========================================================================");
      System.out.println();
      System.out.println("Enter Amount : ");
      System.out.print(">> ");
      card = scanner.nextInt();
      String load = "....";
      
      try{
      System.out.println();
      System.out.print("Load Beep Card Balance");
      
      for(int i = 0;i<load.length();i++){
      System.out.print(load.charAt(i));
      Thread.sleep(800);
      
      
      }
      Thread.sleep(2000);
      
      }catch (InterruptedException e){
        System.out.println(e);
      }
      System.out.println();
      System.out.println("Your new Balance is " + card);
           System.out.println(); System.out.println("==========================================================================");
      mrt();

    }
}
