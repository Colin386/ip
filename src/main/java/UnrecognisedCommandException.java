package main.java;

public class UnrecognisedCommandException extends Exception {

    private String wrongCommand;

    /**
     * Constructor for the unrecognised command exception
     *
     * @param wrongCommand indicates the word that is not recognised by the program
     */
    public UnrecognisedCommandException(String wrongCommand) {
        this.wrongCommand = wrongCommand;
    }

    /**
     * Wrong command getter
     * @return String containing the name of the wrong command detected
     */
    public String getWrongCommand() {
        return wrongCommand;
    }
}
