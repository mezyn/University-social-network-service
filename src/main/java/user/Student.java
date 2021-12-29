package user;

  /*
  Student class is a child class of User class.
  In addition to the basic information that the system takes from all users, Student objects will have two more attributes;
  that is, graduation year and study program.
  This class has only constructor for creating staff objects.
  Relevant methods can be found in UserMethods class in the same package.
  */
public class Student extends User {

  private int graduationYear;
  private String program; //It means study program. Upon account registration a student can select this from a dropdown box.

  /*
  We have two Student constructors.
  The first one (with type 1) is used to create a student object who is not an administrator.
  The second one is used to create a student object who is also an administrator; therefore, it is called in the
  Administrator class in order to create an administrator object.
   */

  public Student(String email, String password, String firstName, String lastName, int graduationYear, String program) throws Exception {
    super(email, password, firstName, lastName, 1); //Type 1 for non-administrator student
    this.graduationYear = graduationYear;
    this.program = program;
  }

  public Student(String email, String password, String firstName, String lastName, int graduationYear, String program, int type) throws Exception {
    /*
    Here the type is empty, which gives possibility to create diverse student types in the future development.
    For the current version it is only used to create an administrator object.
    */
    super(email, password, firstName, lastName, type);
    this.graduationYear = graduationYear;
    this.program = program;
  }

  // Getters and setters for program & graduation year

  public String getProgram () {
    return this.program;
  }

  public int getGraduationYear() {
    return this.graduationYear;
  }

  public void setGraduationYear(int graduationYear) {
    this.graduationYear =  graduationYear;
  }

  public void setProgram (String program) {
    this.program = program;
  }

}

