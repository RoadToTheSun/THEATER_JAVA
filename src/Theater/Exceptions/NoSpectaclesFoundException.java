package Theater.Exceptions;

public class NoSpectaclesFoundException extends TheaterException {
    public NoSpectaclesFoundException() {
        this("No available spectacles found!");
    }

    public NoSpectaclesFoundException(String message) {
        super(message);
    }
}
