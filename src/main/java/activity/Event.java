package main.java.activity;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task implements DateStorage {

    /** String containing date that the event is held*/
    private String atDate;
    private LocalDateTime dueDateTime;

    /**
     * Constructor that makes the event object with the name and date information
     *
     * @param thing String of name of event
     * @param date String containing the date event is held
     */
    public Event(String thing, String date) throws DateTimeParseException{
        super(thing);
        final int AT_LENGTH = 3;
        atDate = date.substring(AT_LENGTH);

        dueDateTime = this.processDate(atDate);
    }

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
        return " (at: " + this.atDate + ")";
    }

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
