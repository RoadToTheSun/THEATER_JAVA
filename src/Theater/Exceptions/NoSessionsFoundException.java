package Theater.Exceptions;

public class NoSessionsFoundException extends TheaterException {
    public NoSessionsFoundException() throws NoSessionsFoundException {
        throw new NoSessionsFoundException("No available sessions found!");
    }

    public NoSessionsFoundException(String message) {
        super(message);
    }
}
