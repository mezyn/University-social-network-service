package user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

/** Student class.
 *
 */
public class Student extends User {

  private int graduationYear;
  //private ArrayList<String> courses;
  //private ArrayList<String> interests;
  private String program;
  //private boolean isAdministrator;
  /** Constructor for Student class.
     *
     */


  public Student(String email, String password, String firstName, String lastName, int graduationYear, String program) throws Exception {
    super(email, password, firstName, lastName, 1);
   //add exceptions
    this.graduationYear = graduationYear;
    this.program = program;
  }

  public Student(String email, String password, String firstName, String lastName, int graduationYear, String program, int type) throws Exception {
    super(email, password, firstName, lastName, type);
    //add exceptions
    this.graduationYear = graduationYear;
    this.program = program;
  }


  public String getProgram () {
    return this.program;
  }

  public void setProgram (String program) {
    this.program = program;
  }

  public int getGraduationYear() {
    return this.graduationYear;
  }

  public void setGraduationYear(int graduationYear) {
    this.graduationYear =  graduationYear;
  }




}

