package main.java.dukeExceptions;

public class MissingDateException extends NotEnoughInfoException {

    /**
     * Constructor for the Missing Date Exception, created when date information for a function is missing
     *
     * @param command String containing the name of the function that is missing the date information
     */
    public MissingDateException(String command) {
        super(command);
    }

    /**
     * Provides a String telling the user which function requires more date information to operate
     *
     * @return String containing name of command tht requires more date information
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "Requires date";
    }

}
