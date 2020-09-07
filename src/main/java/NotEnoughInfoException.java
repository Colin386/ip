package main.java;

public class NotEnoughInfoException extends Exception{

    private String commandName;

    /**
     * Generates a Not Enough Information exception
     *
     * @param commandName a string containing the command that needed more information
     */
    public NotEnoughInfoException(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Getter for the command name
     *
     * @return a string containing the command that triggered the exception
     */
    public String getCommand() {
        return this.commandName;
    }
}
