package main.java;

public class MissingKeywordException extends NotEnoughInfoException {

    protected String missingKeyword;

    public MissingKeywordException(String command, String keyword) {
        super(command);
        this.missingKeyword = keyword;


    }

    @Override
    public String getMessage() {
        String message;
        String keywordMessage;
        keywordMessage = "\nMissing keyword: " + this.missingKeyword;
        message = super.getMessage() + keywordMessage;
        return message;
    }
}
