package event;

import java.util.ArrayList;
import java.util.Date;

/**
 *<p>
 *  Event class acts as an object for storing event information.
 *  This will be handled by EventController class thus, all info is private or package-private.
 * </p>
 */

public class Event {
  private final Date creationDate;
  private final int id;
  //Related to the method of addEvent(String name, Date eventDate, String location, int courseId) in the EventController class,
  // you might want to explain what's the system in providing id to each event;
  // i.e. the first created event gets an id of 1, and the next one 2, ...
  private String title;
  private Date date;
  private String location;
  private int course;
  private boolean active;
  private final ArrayList<Integer> attending; //Does it mean participants/attendees? A comment for explanation would be nice
  private final ArrayList<Integer> guestTutors; //Same here, what does the roll of guest tutors at an event?
  private final ArrayList<Integer> seenBy;

  Event(int id, String title, Date date, String location, int course) {
    this.id = id;
    this.title = title;
    this.date = date;
    this.location = location;
    this.course = course;
    creationDate = new Date();
    attending = new ArrayList<>();
    guestTutors = new ArrayList<>();
    seenBy = new ArrayList<>();
    active = true;
  }

  /*
  ********************
  Retrieve information
  ********************
   */
//Getters and setters
  public String getTitle() {
    return this.title;
  }

  public Date getDate() {
    return this.date;
  }

  public String getLocation() {
    return this.location;
  }

  public int getCourse() {
    return this.course;
  }

  public int getId() {
    return this.id;
  }

  public Integer[] getSeenBy() {
    return seenBy.toArray(new Integer[0]); // converts into a regular array.
  }

  public Integer[] getAttending() {
    return attending.toArray(new Integer[0]); // Converts into an Integer array.
  }
//Getter
  public Date getCreationDate() {
    return creationDate;
  }

  public boolean isActive() {
    return active; // true if active, False if not.
  }

  /*
  *******************
     Modify object 
  *******************
   */
// Write comment here
  public void addAttendee(int id) {
    int index = attending.indexOf(id);
    if (index == -1) { //If no match found
      attending.add(id);
    }
  }
// Write comment here
  public void delAttendee(int id) {
    int index = attending.indexOf(id);
    if (index != -1) {
      attending.remove(index);
    }
  }

  //Add the ID of user who's seen the event.
  public void addSeenBy(int id) {
    int index = seenBy.indexOf(id);
    if (index == -1) {
      seenBy.add(id);
    }
  }
//Getters and setters
  public void setDate(Date newDate) {
    this.date = newDate;
  }

  public void setLocation(String newLocation) {
    this.location = newLocation;
  }

  public void setTitle(String newName) {
    this.title = newName;
  }

  public void setCourse(int newCourse) {
    this.course = newCourse;
  }

  public void setStatus(boolean status) {
    active = status;
  }
}