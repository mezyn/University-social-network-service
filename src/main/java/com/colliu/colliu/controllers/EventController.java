package com.colliu.colliu.controllers;

import com.colliu.colliu.Master;
import com.colliu.colliu.MasterController;
import event.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import miscellaneous.Data;
import user.Administrator;
import user.Staff;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class EventController {

  final String effectOn = "-fx-background-color: #FAFAFA;";
  final String effectOff = "-fx-background-color: none;";

  MasterController master;

  @FXML
  private Button btnAttending;

  @FXML
  private Button btnCreateEvent;

  @FXML
  void onCreateEventClicked(ActionEvent event) throws Exception {
    master.showEventPage(); //I want to use this method but it's private in MasterController. What do I do
  }
  @FXML
  private Button btnLogout;

  @FXML
  private Button closeWindow;

  @FXML
  private VBox eventItems;

  @FXML
  private ScrollPane eventScroll;

  @FXML
  private Label filterCategory;
/*
Not sure how to combine ListView and Checkboxes, so I just put checkboxes in a panel
  @FXML
  private ListView<String> filterList;
  String[] categories = {"Gaming", "Guest Lecture", "Hackathon", "Lunch Lecture","Mingle","Sports","Student Union", "Workshop","Others"};
    */

  @FXML
  private Pane filterPane;

  @FXML
  private ImageView imageProfile;

  @FXML
  private Pane listAnnouncements;

  @FXML
  private Pane titleBar;

  @FXML
  private TextField txtSearch;

  @FXML
  private Button btnProfileSettings;

  @FXML
  private VBox vbNameDropDown;

  @FXML
  private Pane pnUserName;

  @FXML
  private Button btnArrowPoint;

  @FXML
  private CheckBox cb1;

  @FXML
  private CheckBox cb2;

  @FXML
  private CheckBox cb3;

  @FXML
  private CheckBox cb4;

  @FXML
  private CheckBox cb5;

  @FXML
  private CheckBox cb6;

  @FXML
  private CheckBox cb7;

  @FXML
  private CheckBox cb8;

  @FXML
  private CheckBox cb9;

  @FXML
  private Label lblName;

  @FXML
  void closeProgram(ActionEvent event) {

  }

  @FXML
  void filterEvent(MouseEvent event) {

  }

  @FXML
  void logOutUser(ActionEvent event) {
    master.showLogin();
  }

  @FXML
  void openSettings(MouseEvent event) {

  }

  @FXML
  void setOnMouseDragged(MouseEvent event) {

  }

  @FXML
  void setOnMousePressed(MouseEvent event) {

  }

  @FXML
  void showAttendingEvents(ActionEvent event) {

  }

  @FXML
  void viewAnnouncements(MouseEvent event) {

  }


  /*
    This method loads all the events;
    To make it work for tags you can add paremeters as needed:
   */

  public void loadEvents(String[] filters) throws IOException {
    Event[] eventList = master.getCurrentEvents();
    eventItems.getChildren().clear(); // resets VBox contents(event list)
    setName();
    Node[] adds;
    if (filters == null | filters.length == 0) { // Does this make sense? <-- If tag is "All" show all events- Else show only events equal to "tag" parameter
       adds = new StackPane[eventList.length];
      for (int i = 0; i < eventList.length; i++) {
        FXMLLoader eventLoader = new FXMLLoader(Master.class.getResource("fxml/event_design.fxml"));
        adds[i] = eventLoader.load();
        eventItems.getChildren().add(adds[i]);
        eventItems.getChildren().get(0).setLayoutX(0);
        EventItem eventController = eventLoader.getController();
        eventController.setMaster(master);
        eventController.setEvent(eventList[i]);
        eventController.setAttending((eventList[i].getAttending().contains("william@student.gu.se"/*master.userMethods.currentUser*/) ? true : false));
      }
    } else {
      Event[] filteredEvents = master.filterEvents(filters);
      adds = new StackPane[filteredEvents.length];
      for (int i = 0; i < filteredEvents.length; i++) {
        FXMLLoader eventLoader = new FXMLLoader(Master.class.getResource("fxml/event_design.fxml"));
        adds[i] = eventLoader.load();
        eventItems.getChildren().add(adds[i]);
        eventItems.getChildren().get(0).setLayoutX(0);
        EventItem eventController = eventLoader.getController();
        eventController.setMaster(master);
        eventController.setEvent(filteredEvents[i]);
        eventController.setAttending((filteredEvents[i].getAttending().contains("william@student.gu.se"/*master.userMethods.currentUser*/) ? true : false));
        /*eventController.setEventInfo();*/
      }
    }
    eventItems.setSpacing(5);
    eventScroll.setContent(eventItems);
    eventItems.setAlignment(Pos.CENTER);
  }

  @FXML
  void toggleDropDown(MouseEvent event) {
    boolean isClicked = !vbNameDropDown.isVisible();
    vbNameDropDown.setVisible(isClicked);
    String userNameEffectOn = effectOn + "-fx-border-width: 0 1 1 1; -fx-border-style: solid; -fx-border-color: #FAFAFA;";
    String userNameEffectOff = effectOn;
    pnUserName.setStyle((isClicked ? userNameEffectOn : userNameEffectOff));
    btnArrowPoint.setText((isClicked ? "▲" : "▼"));
  }

  @FXML
  void openProfileSettings(ActionEvent event) {

  }

  @FXML
  void logOutUser(MouseEvent event) {

  }

  @FXML
  void hoverEffectOn(MouseEvent event) {
    ((Button) event.getSource()).setStyle(effectOn);
  }

  @FXML
  void hoverEffectOff(MouseEvent event) {
    ((Button) event.getSource()).setStyle(effectOff);
  }

  @FXML
  void nameHoverOn(MouseEvent event) {
    pnUserName.setStyle(effectOn);
  }

  @FXML
  void nameHoverOff(MouseEvent event) {
    pnUserName.setStyle(effectOff);
  }

  @FXML
  void onFilterClick(ActionEvent event) throws Exception {
    ArrayList<String> tags = new ArrayList<>();

    if(cb1.isSelected()) {
      tags.add("Gaming");
    }
    if(cb2.isSelected()) {
      tags.add("Guest Lecture");
    }
    if(cb3.isSelected()) {
      tags.add("Hackathon");
    }
    if(cb4.isSelected()) {
      tags.add("Lunch lecture");
    }
    if(cb5.isSelected()) {
      tags.add("Mingle");
    }
    if(cb6.isSelected()) {
      tags.add("Sports");
    }
    if(cb7.isSelected()) {
      tags.add("Student Union");
    }
    if(cb8.isSelected()) {
      tags.add("Workshop");
    }
    if(cb9.isSelected()) {
      tags.add("Others");
    }

    String[] tagsToFilter = tags.toArray(new String[0]);
    loadEvents(tagsToFilter);
  }

  @FXML
  void createNewEvent(ActionEvent event) throws IOException {
      master.showEventCreationPage();
  }

  public void setMaster(MasterController master) {
    this.master = master;
  }

  public void onButtonPressOpenSettingsPage() throws Exception {
    master.showProfileSettingsPage();
  }

  @FXML
  void changeName(MouseEvent event) {
    //((Label)event.getSource()).setText(master.user.getFirstName() + ((Staff) master.user).getDepartment());
  }

  private void setName() {
    String name = master.getCurrentUser().getFirstName() + " " + master.getCurrentUser().getLastName();
    lblName.setText(name);
  }
}
