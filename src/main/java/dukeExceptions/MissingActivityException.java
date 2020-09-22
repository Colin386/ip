package main.java.dukeExceptions;

public class MissingActivityException extends NotEnoughInfoException {

    /**
     * Constructor for the MissingActivityException
     * exception is used when the user did not enter the name of an activity when trying to construct it
     * @param command contains the command that first threw this exception
     */
    public MissingActivityException(String command) {
        super(command);
    }

    /**
     * Provides a string informing the user what information is missing
     * @return String containing the user function that thew the exception
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "Requires activity";
    }


}
