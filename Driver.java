import java.util.Scanner;

/* Driver.java 
 * where the program runs
 * 
 * @author Alexis Luo
 */

public class Driver {
	public static void main(String[] args) {
		//Profile user;
        Database database = new Database();
        //HashedDictionary profilesList;
        
        Scanner scan = new Scanner(System.in);
        boolean runningApp = true;
        
        System.out.println();
        System.out.println("| ------ Welcome to facebook! ------ |");
        System.out.println();

        while (runningApp){

            System.out.println("Select an option: ");
            System.out.println("(1) Create new profile ");
            System.out.println("(2) Login to an existing account ");
            System.out.println("(3) Quit program "); 

            String input = scan.nextLine();
            System.out.println();
            
            if (input.equals("1")){ //create new profile
                database.createProfile();
            }
            
            else if (input.equals("2")){ //login to an existing account
                System.out.println("What is your account username?");
                String accName = scan.nextLine();
                //search for username in hashdictionary
                if (database.getProfilesList().contains(accName)){ 
                    //username exists! find username profile, then continue with actions 
                    Profile user = database.getProfilesList().getValue(accName); 
                    System.out.println("Successfully logged in!");
                    System.out.println();
                    
                    database.loginToProfile(user);
                }
                else{
                    System.out.println("Username not found. Try again.");
                }
                
            }        
            
            else if (input.equals("3")){ //quit program
                runningApp = false;
            }
            else {
                System.out.println("Invalid option. Try again. ");
            }

        }
        
        System.out.println("| --- Thanks for using facebook! --- |");
        System.out.println();
    }
}