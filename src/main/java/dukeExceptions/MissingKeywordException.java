package main.java.dukeExceptions;

public class MissingKeywordException extends NotEnoughInfoException {

    protected String missingKeyword;

    /**
     * Constructor for MissingKeywordException, created if a command is missing a keyword option, e.g. deadline called without the /by keyword
     *
     * @param command String containing the command that created the exception due to missing keyword
     * @param keyword String, usually indicating a missing option labelled with a / symbol
     */
    public MissingKeywordException(String command, String keyword) {
        super(command);
        this.missingKeyword = keyword;


    }

    /**
     * Retrieves a message to be printed to inform the user about the error
     *
     * @return String with more details on the error such as which function called the error and what keyword is missing
     */
    @Override
    public String getMessage() {
        String message;
        String keywordMessage;
        keywordMessage = "\nMissing keyword: " + this.missingKeyword;
        message = super.getMessage() + keywordMessage;
        return message;
    }
}
