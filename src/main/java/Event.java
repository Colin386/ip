package main.java;

public class Event extends Task {

    private String atDate;

    public Event(String thing, String date){
        super(thing);
        final int AT_LENGTH = 3;
        atDate = date.substring(AT_LENGTH);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.atDate + ")";
    }
}
