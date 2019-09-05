package ua.com.monobank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MnemonicNotFoundException extends RuntimeException {

    public MnemonicNotFoundException(String message) {
        super(message);
    }

}
