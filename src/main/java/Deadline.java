package main.java;

public class Deadline extends Task{

     /**Deadline of the task*/
    private String byDate;

    /**
     * Construct a deadline object with name info and deadline info
     *
     * @param thing String containing the name of the task
     * @param date String containing the date information
     */
    public Deadline(String thing, String date) {
        super(thing);
        final int BY_LENGTH = 3;
        this.byDate = date.substring(BY_LENGTH);
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

    /**
     * Function returns the task information in a formatted string
     *
     * @return String containing the Deadline tag, the task name and the due date
     */
    public String toString() {

        return "[D]" + super.toString() + this.formatDate();
    }
}
