package Theater.Exceptions;

public class NoCustomersFoundException extends TheaterException{
    public NoCustomersFoundException() {
        this("No customers found!");
    }

    public NoCustomersFoundException(String message) {
        super(message);
    }
}

