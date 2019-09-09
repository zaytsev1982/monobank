package ua.com.monobank.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.com.monobank.exception.entityerror.ErrorHandler;

@ControllerAdvice
public class CustomGlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MnemonicNotFoundException.class)
    public ResponseEntity<ErrorHandler> errorHandlerMnemonic(MnemonicNotFoundException ex) {
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setLocalDateTime(LocalDateTime.now());
        errorHandler.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
        errorHandler.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorHandler, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReferenceBookNotFoundException.class)
    public ResponseEntity<ErrorHandler> errorHandlerBook(ReferenceBookNotFoundException ex) {
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setLocalDateTime(LocalDateTime.now());
        errorHandler.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
        errorHandler.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorHandler, HttpStatus.NOT_FOUND);
    }
}
