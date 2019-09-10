package ua.com.monobank.exception;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.com.monobank.exception.entityerror.ErrorHandler;

@RestControllerAdvice
@Slf4j
public class CustomGlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MnemonicNotFoundException.class)
    public ResponseEntity<ErrorHandler> errorHandlerMnemonic(MnemonicNotFoundException ex,
        WebRequest request) {
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setLocalDateTime(LocalDateTime.now());
        errorHandler.setMessage(ex.getMessage());
        errorHandler.addDetails(request.getDescription(false));
        return new ResponseEntity<>(errorHandler, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReferenceBookNotFoundException.class)
    public ResponseEntity<ErrorHandler> errorHandlerBook(ReferenceBookNotFoundException ex,
        WebRequest request) {
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setLocalDateTime(LocalDateTime.now());
        errorHandler.setMessage(ex.getMessage());
        errorHandler.addDetails(request.getDescription(false));
        return new ResponseEntity<>(errorHandler, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> error(RuntimeException ex) {
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setLocalDateTime(LocalDateTime.now());
        errorHandler.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorHandler, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
