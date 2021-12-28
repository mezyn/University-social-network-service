package user;

import com.colliu.colliu.MasterController;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


/**
 *********************************
 * Methods related to the users. *
 *********************************
 **/
public class UserMethods {
  private ArrayList<User> users;
  MasterController master;
  private User currentUser;
  // private String loggedInUserEmail;

  public UserMethods(MasterController master) {
    this.master = master;
    users = master.loadUsers();
  }

  //create user and sub-types, check for existing users (email?), add / not add into arraylist
  // update name, update password, update graduationYear,
  // limit user-permission & do exceptions (instanceOf etc.)
  // check when user latest logged in(dunno how)? william wrote about it
  //get a user by email?
  //Using user input + calling constructors from the different user-classes?
  // Write comment here
  public int findUser(String searchEmail) {
    if (users.size() == 0) {
      return -1;
    }
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getEmail().equals(searchEmail)) {
        return i;
      }
    }
    return -1;
    /*
    A cleaner way to do this is:
    return activeUsers.indexOf(searchEmail);
    this will return either the index of the arraylist or -1 if it doesn't exist
    // William.
     */
  }

  public boolean testMethod(String email, String password) {
    User userToTest = getUserByEmail(email); // Fetch user object if email exist else fetch null
    if(userToTest != null && userToTest.validatePassword(password)) { // Test if object is a user(or null) and then test if password is correct
      // Let user login!
    } else {
        // Wrong email or password
    }
    return true; // change this
  }
// Write comment here
  public String createStudent(String email, String password, String firstName, String lastName, int graduationYear, String program) throws Exception {
    users.add(new Student(email, password, firstName, lastName, graduationYear, program));
    return "User registered successfully.";
  }
// Write comment here
  public String createAdministrator(String email, String password, String firstName, String lastName, int graduationYear, String program) throws Exception {
    users.add(new Administrator(email, password, firstName, lastName, graduationYear, program));
    return "User registered successfully.";
  }
// Write comment here
  public String createStaff(String email, String password, String firstName, String lastName) throws Exception {
    users.add(new Staff(email, password, firstName, lastName));
    return "User registered successfully.";
  }
  /*
    public boolean userLogin(String email, String password) {

      if (checkExistingEmail(email) && validatePassword(password)) {
        return true;
      }
      return false;
    }
   */


// Write comment here
  public boolean checkExistingEmail(String email) {

    for (User user : users) {
      if (user.getEmail().equals(email)) {
        return true;
      }
    }
    return false;
  }
// Write comment here
//I'm not sure if the validatePassword method is logically correct.
//So if the password input exist in the system it validates it no matter which user does the password belongs to? -Mijin
  public boolean validatePassword(String password, String email) {

    for (User user : users) {
      if (user.getPassword().equals(password) && user.getEmail().equals(email)) {
        return true;
      }
    }

    return false;
  }
// Write comment here
  public int getIndex(int id, ArrayList<Integer> list) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) == id) {
        return i;
      }
    }
    return -1;
  }
// Write comment here
  public User getUserByEmail(String email) {
    for (User user: users) {
      if (user.getEmail().equals(email)) {
        return user;
      }
    }
    return null;
  }

  public boolean checkPasswordComplexity(String password) {
    if (password == null || password.isBlank()) {
      return false;
    }
    if (password.length() < 11 || password.length() > 20) {
      return false;    }
    if (!password.matches("(.*[A-Z].*)")) {
      return false;    }
    if (!password.matches("(.*[a-z].*)")) {
      return false;    }
    if (!password.matches("(.*[0-9].*)")) {
      return false;    }
    if (password.contains(" ")) {
      return false;
    } else {
      return true;
    }
  }

  public void setLoggedInUser(User user) {
    currentUser = user;
  }

  public boolean getAccountStatus(String email) {
    return currentUser.getAccountStatus();
  }

  public User getLoggedInUser() {
    return currentUser;
  }

  public int getCurrentUserType() {
    return currentUser.getType();
  }

  public void promoteStudentToAdmin(String promoteEmail) throws Exception {
   Student promoteUser = (Student) getUserByEmail(promoteEmail);
   String email = promoteUser.getEmail();
   String password = promoteUser.getPassword();
   String firstName = promoteUser.getFirstName();
   String lastName = promoteUser.getLastName();
   String program = promoteUser.getProgram();
   int graduationYear = promoteUser.getGraduationYear();
   users.remove(findUser(email));
   createAdministrator(email, password, firstName, lastName, graduationYear, program);
   master.saveUsers();
  }

  public void banUser(String userToBan)  {
    getUserByEmail(userToBan).setAccountStatus(true);
    master.saveUsers();
  }


  public void unbanUser(String userToUnban)  {
    getUserByEmail(userToUnban).setAccountStatus(false);
    master.saveUsers();
  }

  public ArrayList<User> getAllUsers() {
    return users;
  }

}
