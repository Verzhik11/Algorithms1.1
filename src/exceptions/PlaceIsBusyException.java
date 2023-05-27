package exceptions;

public class PlaceIsBusyException extends RuntimeException {
    public PlaceIsBusyException() {
    }

    public PlaceIsBusyException(String message) {
        super(message);
    }
}
