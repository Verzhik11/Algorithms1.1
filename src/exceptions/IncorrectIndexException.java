package exceptions;

public class IncorrectIndexException extends IllegalArgumentException {
    public IncorrectIndexException(String s) {
        super(s);
    }
}
