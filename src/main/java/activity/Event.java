package main.java.activity;

public class Event extends Task {

    /** String containing date that the event is held*/
    private String atDate;

    /**
     * Constructor that makes the event object with the name and date information
     *
     * @param thing String of name of event
     * @param date String containing the date event is held
     */
    public Event(String thing, String date){
        super(thing);
        final int AT_LENGTH = 3;
        atDate = date.substring(AT_LENGTH);
    }

    /**
     * Functions return a formatted string of the date information
     *
     * @return String containing the original date written with "at" added
     */
    public String formatDate() {
        return " (at: " + this.atDate + ")";
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
}
