package main.java.activity;


import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


public class Deadline extends Task implements DateStorage{

     /**Deadline of the task*/
    private String byDate;
    private LocalDateTime dueDateTime;


    /**
     * Construct a deadline object with name info and deadline info
     *
     * @param thing String containing the name of the task
     * @param date String containing the date information
     */
    public Deadline(String thing, String date) throws DateTimeParseException {
        super(thing);
        final int BY_LENGTH = 3;
        this.byDate = date.substring(BY_LENGTH);

        this.dueDateTime = processDate(this.byDate);

    }



    /**
     * Function returns the date in a formatted string
     *
     * @return String containing the date with the word "by" attached to it
     * no further formatting done
     */
    public String formatDate() {
        return " (by: " + this.byDate + ")";
    }


    @Override
    public LocalDateTime retrieveDate() {
        return this.dueDateTime;
    }


    private LocalDateTime processDate  (String userDate) throws DateTimeParseException {
        String[] dateTimeInfo = userDate.split(" ");

        String dateInfo = dateTimeInfo[0];
        String timeInfo;
        LocalDateTime newDate;

        if (dateTimeInfo.length <= 1) { //no time given by user, assume time to be midnight
            timeInfo = "00:00:00";
            this.byDate = this.byDate + " " + timeInfo;
        }
        else {
            timeInfo = dateTimeInfo[1];
        }

        newDate = LocalDateTime.parse(dateInfo + "T" + timeInfo);
        return newDate;

    }

    /**
     * Function returns the task information in a formatted string
     *
     * @return String containing the Deadline tag, the task name and the due date
     */
    public String toString() {

        return "[D]" + super.toString() + this.formatDate();
    }

    /**
     * Function returns the date information of the event
     *
     * @return String containing the date
     */
    public String getFullDateString() {
        return this.byDate;
    }
}
