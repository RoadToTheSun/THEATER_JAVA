package Theater.Exceptions;

public abstract class TheaterException extends Exception {

    public TheaterException() {}

    public TheaterException(String message) {
        super(message);
    }
}
