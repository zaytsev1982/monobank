package ua.com.monobank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ClientConnectException extends RuntimeException {

    public ClientConnectException(String message) {
        super(message);
    }
}
