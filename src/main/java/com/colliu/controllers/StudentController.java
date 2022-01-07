package com.colliu.controllers;

import com.colliu.PageController;
import com.colliu.miscellaneous.Info;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.colliu.miscellaneous.Style;
import com.colliu.user.UserMethods;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
  private PageController master;
  private UserMethods userMethods;

  @FXML
  private Label lblWarning;

  @FXML
  private PasswordField tfConfirmPassword;

  @FXML
  private TextField tfFirstName;

  @FXML
  private TextField tfLastName;

  @FXML
  private PasswordField tfPassword;

  @FXML
  private ChoiceBox<String> graduationYearChoice;

  @FXML
  private ChoiceBox<String> programChoice;

  //Creates an array of strings with the viable options for graduation year, they are used in a choice box
  private final String[] graduationYear = {String.valueOf((Year.now().getValue())),
      String.valueOf(Year.now().getValue() + 1), String.valueOf(Year.now().getValue() + 2),
      String.valueOf(Year.now().getValue() + 3)};

  //Creates an array of strings with the viable options for program, they are used in a choice box
  private final String[] program = {Info.SEM, Info.SVET, Info.KOG, Info.DVET};

  //Returns value of an option alternative in a choice box
  public int getGraduationYear() {
    return Integer.parseInt(graduationYearChoice.getValue());
  }

  //Returns value of an option alternative in a choice box
  public String getProgram() {
    return programChoice.getValue();
  }

  @FXML
  private TextField tfStudentEmail;

  // Tells .user what input is wrong if it is, otherwise it creates a student
  // I dont think we should throw exceptions since we do not catch them,
  // and have a conditional checking already / William
  @FXML
  void registerStudent() throws Exception {
    // Checks if the Email that the user entered is blank,m ends with student.gu.se,contains any blank spaces or if the email already exists in the system. Informs the .user if any of these are fulfilled.
    if (tfStudentEmail.getText().isBlank()) {
      lblWarning.setText("Email cannot be blank");
      tfStudentEmail.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("Email name cannot be blank");
    } else if (!tfStudentEmail.getText().endsWith("@student.gu.se")) {
      lblWarning.setText("Email must be a GU provided student address.");
      tfStudentEmail.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("Email must be a GU provided student address.");
    } else if (tfStudentEmail.getText().contains(" ")) {
      lblWarning.setText("Email cannot contain any blank spaces");
      tfStudentEmail.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("Email cannot contain any blank spaces");
    } else if (tfStudentEmail.getText().chars().filter(num -> num == '@').count() > 1
        || tfStudentEmail.getText().startsWith("@")) {
      lblWarning.setText("Email is invalid.");
      tfStudentEmail.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("Email is invalid.");
    } else {
      lblWarning.setText("");
      tfStudentEmail.setStyle(Style.TEXTFIELD_GREEN);
    }
    if (userMethods.getUserByEmail(tfStudentEmail.getText()) != null) {
      lblWarning.setText("Email is already registered");
      throw new Exception("Email is already registered");
    } else {
      lblWarning.setText("");
    }
    // Checks if the first name that the .user entered is blank or contains any numbers. Informs the .user if any of these are fulfilled.
    if (tfFirstName.getText().isBlank()) {
      lblWarning.setText("First name cannot be blank");
      tfFirstName.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("First name cannot be blank");
    } else if (tfFirstName.getText().matches(("(.*[0-9].*)"))) {
      lblWarning.setText("First name cannot contain any numbers");
      tfFirstName.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("First name cannot contain any numbers");
    } else {
      lblWarning.setText("");
      tfFirstName.setStyle(Style.TEXTFIELD_GREEN);
    }
    // Write comment here
    if (tfLastName.getText().isBlank()) {
      lblWarning.setText("Last name cannot be blank.");
      tfLastName.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("Last name cannot be blank");
    } else if (tfLastName.getText().matches(("(.*[0-9].*)"))) {
      lblWarning.setText("Last name cannot " +  System.lineSeparator() + " contain any numbers");
      tfLastName.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("Last name cannot contain any numbers");
    } else {
      lblWarning.setText("");
      tfLastName.setStyle(Style.TEXTFIELD_GREEN);
    }
    // // Checks that password is between 12-20 characters, that it has at least one uppercase and one lowercase character, that it contains at least one number, that it doesn't contain any blank spaces and that the password and confirm password text field matches.
    if (tfPassword.getText().isBlank()) {
      lblWarning.setText("Password cannot be blank");
      tfPassword.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("Password cannot be blank");
    } else if (tfPassword.getText().length() < 11 || tfPassword.getText().length() > 20) {
      lblWarning.setText("Password must be between 12 and 20 characters");
      tfPassword.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("Password must be between 12 and 20 characters");
    } else if (!tfPassword.getText().matches("(.*[A-Z].*)")) {
      lblWarning.setText("Password must contain at least one uppercase letter");
      tfPassword.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("Password must contain at least one uppercase letter");
    } else if (!tfPassword.getText().matches("(.*[a-z].*)")) {
      lblWarning.setText("Password must contain at least one lowercase character");
      tfPassword.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("Password must contain at least one lowercase character");
    } else if (!tfPassword.getText().matches("(.*[0-9].*)")) {
      lblWarning.setText("Password must contain at least one number");
      tfPassword.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("Password must contain at least one number");
    } else if (tfPassword.getText().contains(" ")) {
      lblWarning.setText("Passwords cannot contain blank spaces");
      tfPassword.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("Passwords cannot contain blank spaces");
    }
    if (!tfPassword.getText().equals(tfConfirmPassword.getText())) {
      lblWarning.setText("Passwords does not match");
      tfPassword.setStyle(Style.TEXTFIELD_RED);
      throw new Exception("Passwords does not match");
    } else {
      lblWarning.setText("");
      tfPassword.setStyle(Style.TEXTFIELD_GREEN);
    }
    // Puts the .user input into variables and then creates a staff .user. This also saves the created .user into a JSon file and then redirects you to the login page.
    try {
      String email = tfStudentEmail.getText();
      String password = tfPassword.getText();
      String name = tfFirstName.getText();
      String surname = tfLastName.getText();
      int graduationYear = getGraduationYear();
      String program = getProgram();
      userMethods.createStudent(email, password, name, surname, graduationYear, program);
      master.saveUsers();
      master.showLogin();
    } catch (Exception exception)  {
      exception.printStackTrace();
    }
  }

  //Button that cancels registration and brings you back to the login page
  @FXML
  void cancelRegistration(ActionEvent event) {
    master.showLogin();
  }

  public void setMaster(PageController master) {
    this.master = master;
    this.userMethods = master.getUserReference();
  }

  //Initializes and gives options to the choice boxes
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    graduationYearChoice.getItems().addAll(graduationYear);
    graduationYearChoice.getSelectionModel().select(0);
    programChoice.getItems().addAll(program);
    programChoice.getSelectionModel().select(0);
  }
}