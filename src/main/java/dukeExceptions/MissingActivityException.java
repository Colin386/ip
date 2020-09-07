package main.java.dukeExceptions;

public class MissingActivityException extends NotEnoughInfoException {
    public MissingActivityException(String command) {
        super(command);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Requires activity";
    }


}
