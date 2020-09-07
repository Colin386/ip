package main.java;

public class MissingDateException extends NotEnoughInfoException {

    public MissingDateException(String command) {
        super(command);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Requires date";
    }

}
