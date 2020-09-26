package main.java.activity;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task implements DateStorage {

    /** String containing date that the event is held*/
    private String atDate;
    private LocalDateTime dueDateTime;
    private DateParser d;

    /**
     * Constructor that makes the event object with the name and date information
     *
     * @param thing String of name of event
     * @param date String containing the date event is held
     */

    /**
     * Constructor that makes the event object with the name and date information
     *
     * @param thing String of name of event
     * @param date String containing the date of the event entered by user
     * @throws DateTimeParseException occurs if user did not key in a date in the form of yyyy-mm-dd
     */
    public Event(String thing, String date) throws DateTimeParseException{
        super(thing);
        d = new DateParser();
        final int AT_LENGTH = 3;
        atDate = date.substring(AT_LENGTH);

        dueDateTime = this.processDate(atDate);

    }

    /**
     * Function returns the date information of the event in a formatted string
     *
     * @return String containing the date
     */
    @Override
    public String getFullDateString() {
        return this.atDate;

    }

    /**
     * Functions return a formatted string of the date information
     *
     * @return String containing the original date written with "at" added
     */
    public String formatDate() {
        return " (at: " + this.d.convertToLongDateTime(this.dueDateTime) + ")";
    }

    /**
     * Function returns the date and time in a Datetime object
     *
     * @return LocalDateTime object containing the date and time of the activity
     */
    @Override
    public LocalDateTime retrieveDate() {
        return this.dueDateTime;
    }

    /**
     * Overrides the toString method to display the event tag, name of event and its date
     *
     * @return String containing event tag, name of event and the date of event.
     */
    @Override
    public String toString() {

        return "[E]" + super.toString() + this.formatDate();
    }

    /**
     * Function reads the date and time entered by the user and tries to form a date time object to be stored with the activity
     *
     * @param userDate String containing the user input date and time
     * @return LocalDateTime object according to the user's date and time written
     * @throws DateTimeParseException when user does not input the date time in the form yyyy-mm-dd hh:mm
     */
    private LocalDateTime processDate  (String userDate) throws DateTimeParseException {
        String[] dateTimeInfo = userDate.split(" ");

        String dateInfo = dateTimeInfo[0];
        String timeInfo;
        LocalDateTime newDate;

        if (dateTimeInfo.length <= 1) { //no time given by user, assume time to be midnight
            timeInfo = "00:00:00";
            this.atDate = this.atDate + " " + timeInfo;
        }
        else {
            timeInfo = dateTimeInfo[1];
        }

        newDate = LocalDateTime.parse(dateInfo + "T" + timeInfo);

        return newDate;

    }




}
