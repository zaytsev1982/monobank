package ua.com.monobank.exception;

public class ReferenceBookNotFoundException extends RuntimeException {

    public ReferenceBookNotFoundException(String message) {
        super(message);
    }
}
