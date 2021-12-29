package user;

import java.time.LocalDateTime;

/** Staff class.
 *
 */
public class Staff extends User {
  private String title;
  private String department;

  /** Constructor for Student class.
   *
   */

  public Staff(String email, String password, String firstName, String lastName, String department, String title) throws Exception{
    super(email, password, firstName, lastName, 3);
    //add exception
    this.department = department;
    this.title = title;
  }
  // Getters and setters
  public String getTitle() {
    return this.title;
  }

  public String getDepartment() {
    return this.department;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDepartment(String department) {
    this.department = department;
  }



}
