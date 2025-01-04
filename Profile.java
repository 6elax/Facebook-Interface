import java.util.ArrayList;

/* Profile.java 
 * a Profile class that holds information of a user's profile which includes
 * the user's username, full name, email, phone number, and list of friends
 * 
 * @author Alexis Luo
 */

public class Profile {
    private String userName;
    private ArrayList<Profile> friends;
    private String firstName;
    private String lastName;

    //constructors 
    public Profile(){
        userName = "";
        friends = new ArrayList<Profile>();
        firstName = "";
        lastName = "";
    }

    public Profile(String userName, String first, String last) {
        this.userName = userName;
        this.friends = new ArrayList<Profile>();
        this.firstName = first;
        this.lastName = last;
    }


    //getters and setters
    public String getName() {
        return userName;
    }
    public void setName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Profile> getFriendList(){
        return friends;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public boolean addFriend(Profile friendToAdd) {
        if(friends.contains(friendToAdd)) {
            //System.out.println(friendToAdd.getFirstName() + " " + friendToAdd.getLastName() + " is already a friend.");
            return false;
        }
        else {
            friends.add(friendToAdd);
            return true;
        }
    }

    public boolean removeFriend(Profile friendToRemove) {
        if(friends.contains(friendToRemove)) {
            friends.remove(friendToRemove);
            return true;
        }
        else {
            //System.out.println(friendToRemove.getFirstName() + " " + friendToRemove.getLastName() + " is not on the friend's list");
            return false;
        }
    }

    public void displayProfile(){ //displays full name, username, and friends list of user
        System.out.println(" . ----------");
        System.out.println(" | USERNAME: " );
        System.out.println(" |   " + getName());
        System.out.println(" | NAME: " );
        System.out.println(" |   " + getFirstName() + " " + getLastName());
        System.out.println(" | LIST OF FRIENDS (PEOPLE FOLLOWING): " );
        for (int i=0; i<friends.size(); i++){
            Profile aFriend = friends.get(i);
            System.out.println(" |   " + aFriend.getName() + " - " + aFriend.getFirstName() + " " + aFriend.getLastName());
        }
        System.out.println(" . ----------");
        System.out.println();

    }

}