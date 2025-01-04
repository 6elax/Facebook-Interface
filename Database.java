import java.util.*;

/* Database.java 
 * a database that contains a list of all the profiles created
 * 
 * @author Alexis Luo
 */

public class Database {   
    private HashedDictionary<String, Profile> profilesList = new HashedDictionary<>();
    Scanner scan = new Scanner(System.in);


    public void Database(){
    }

    public HashedDictionary<String, Profile> getProfilesList(){
        return profilesList;
    }


    //creates a new profile and adds it to the database, profilesList
    public void createProfile(){
        boolean exist = true;
        while (exist){
            System.out.println("What would you like your username to be?");
            String userName = scan.nextLine();
            if (profilesList.contains(userName)){
                System.out.println("The username already exists.");
            }
            else{
                Profile newUser = new Profile();
                newUser.setName(userName);

                System.out.print("Enter your first name: ");
                String first = scan.nextLine();
                newUser.setFirstName(first);

                System.out.print("Enter your last name: ");
                String last = scan.nextLine();
                newUser.setLastName(last);

                profilesList.add(userName, newUser);
                System.out.println("Succesfully created a new account!");
                System.out.println();
                exist = false;
            }
        }
    }

    //checks if a user's friends are still in the database
    //removes friends if they are no longer in the database
    public void checkFriends(Profile user){
        for (int i=0; i<user.getFriendList().size(); i++){
            Profile friendUser = user.getFriendList().get(i);
            if (!profilesList.contains(friendUser.getName())){
                user.removeFriend(friendUser);
            }
        }
    }
    
    //provides a user options to change their account username/friends
    public void modifyProfile(Profile user){
        System.out.println("Please select what you would like to modify:");
        System.out.println("(1) Change username");
        System.out.println("(2) Update friends");

        String modify = scan.nextLine();
        System.out.println();

        if (modify.equals("1")){ //renaming username
            boolean exist = true;
            while (exist){
                System.out.println("What would you like your new username to be?");
                String newName = scan.nextLine();
                if (profilesList.contains(newName)){ //username already exists
                    System.out.println("The username already exists.");
                }
                else{                                //username not already used
                    //first remove original profile with old name
                    String oldName = user.getName();
                    profilesList.remove(oldName);
                    //now add in the updated user with new name for the key
                    // it is inconvenient, but not sure how else to make convenient
                    user.setName(newName);
                    profilesList.add(newName, user);

                    System.out.println("Successfully changed username to " + newName);
                    exist = false;
                }
            }
        }
        else if (modify.equals("2")){
            update(user);
        }
        else{
            System.out.println("Bad input.");
        }

        System.out.println();
    }

    //logins a user and gives them options to access their account
    public void loginToProfile(Profile user){

        System.out.println("  ------- Welcome, " + user.getFirstName() + "! -------  ");

        checkFriends(user);

        boolean nextOpt = true;
        while (nextOpt){
            System.out.println("Select an option:");
            System.out.println("(1) View profile ");
            System.out.println("(2) Edit profile ");
            System.out.println("(3) Search for other profiles");
            System.out.println("(4) Delete profile ");
            System.out.println("(5) Log out and return to home screen ");
            String modOpt = scan.nextLine();
            System.out.println();

            if (modOpt.equals("1")){
                user.displayProfile();
            }
            else if (modOpt.equals("2")){
                modifyProfile(user);
            }
            
            else if (modOpt.equals("3")){ //reading profiles

                //print out list of profiles
                displayProfileList();
                System.out.println();


                boolean cont = true;
                while (cont){
                    System.out.println();
                    System.out.println("Which profile would you like to look at?");
                    String readProfile = scan.nextLine();

                    Profile friendProfile = read(readProfile);
                    if (friendProfile != null){
                        System.out.println();
                        friendProfile.displayProfile();
                        System.out.println();
                        update(user);
                    }

                    System.out.println("Would you like to continue looking at profiles? (Y/N)");
                    String contLook = scan.nextLine().toLowerCase();
                    if (contLook.equals("n")){
                        cont = false;
                    }
                }
                System.out.println();

            }
            
            else if (modOpt.equals("4")){
                deleteProfile(user);
                nextOpt = false;
            }

            else if (modOpt.equals("5")){
                nextOpt = false;
                System.out.println("Logged off!");
            }

            else{
                System.out.println("Invalid option.");
            }
        }

    }

    //deletes a profile and removes it from the database
    public void deleteProfile(Profile user){
        System.out.println("Are you sure you want to delete your profile? (Y/N)");
        String deleteProfile = scan.nextLine().toLowerCase();
        if (deleteProfile.equals("y")){
            profilesList.remove(user.getName());
            System.out.println("Successfully deleted profile from database.");
        }
        else if (deleteProfile.equals("n")){
            System.out.println("Profile was not deleted.");
        }
        else{
            System.out.println("Invalid input.");
        }
    }

    //finds and returns the user being searched for, if in the database
    public Profile read(String username) {
        if (profilesList.contains(username)) {
            return profilesList.getValue(username);
        } else {
            System.out.println("Profile not found");
            return null;
        }
    }

    //asks user if they want to add/remove friends
    public void update(Profile user) {

        if (user == null) {
            System.out.println("Profile for " + user.getName() + " not found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to: ");
        System.out.println("(1) Add Friend ");
        System.out.println("(2) Remove Friend ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        if ((choice == 1) || (choice == 2)){
            System.out.print("Enter the friend's username: ");
            String friendName = scanner.nextLine();
            if (friendName.equals(user.getName())){
                System.out.println("You cannot add yourself as a friend.");
        	    return;
            }
            else if (profilesList.contains(friendName)){
                Profile aFriend = profilesList.getValue(friendName);
                if (choice == 1){
                    if (user.addFriend(aFriend))
                        System.out.println("Friend added successfully.");
                    else
                        System.out.println(friendName + " is already a friend.");
                }
                else if (choice == 2){
                    if (user.removeFriend(aFriend))
                        System.out.println("Friend removed successfully.");
                    else
                    System.out.println(friendName + " is not on the friend's list");
                }
            }
            else{
                System.out.println("The profile does not exist."); }
        }
        else{
            System.out.println("Invalid option. Please choose 1 or 2.");
        }
        
    }

    //prints out a list of profiles
    public void displayProfileList() {
        System.out.println("Here are a list of profiles in the database: ");
        Iterator<String> keyIterator = profilesList.getKeyIterator();
        System.out.println("---");
        while (keyIterator.hasNext()) {
            String username = keyIterator.next();
            System.out.println("|  • " + username);
        }
        System.out.println("-----");

    }


}


