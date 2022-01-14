package Theater.Exceptions;

public class NoCollectiveFoundException extends TheaterException {
    public NoCollectiveFoundException() {
        this("No requested collective found!");
    }

    public NoCollectiveFoundException(String message) {
        super(message);
    }
}
