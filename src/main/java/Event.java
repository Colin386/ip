package main.java;

public class Event extends Task {

    private String atDate;

    public Event(String thing, String date){
        super(thing);
        final int AT_LENGTH = 3;
        atDate = date.substring(AT_LENGTH);
    }

    public String formatDate() {
        return " (at: " + this.atDate + ")";
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + this.formatDate();
    }
}
