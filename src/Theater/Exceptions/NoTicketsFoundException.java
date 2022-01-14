package Theater.Exceptions;

public class NoTicketsFoundException extends TheaterException {
    public NoTicketsFoundException() {
        this("No available tickets found!");
    }

    public NoTicketsFoundException(String message) {
        super(message);
    }
}
