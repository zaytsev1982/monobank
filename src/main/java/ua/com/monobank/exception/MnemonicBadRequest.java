package ua.com.monobank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MnemonicBadRequest extends RuntimeException {

    public MnemonicBadRequest(String message) {
        super(message);
    }
}
