package main.java;

public class Deadline extends Task{

    private String byDate;

    public Deadline(String thing, String date) {
        super(thing);
        final int BY_LENGTH = 3;
        this.byDate = date.substring(BY_LENGTH);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDate + ")";
    }
}
