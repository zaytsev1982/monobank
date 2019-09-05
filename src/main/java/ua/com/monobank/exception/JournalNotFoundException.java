package ua.com.monobank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JournalNotFoundException extends RuntimeException {

    public JournalNotFoundException(String message) {
        super(message);
    }
}
