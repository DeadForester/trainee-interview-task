package dev.mysklad.handler;

import dev.mysklad.exception.DataIsIncorrect;
import dev.mysklad.exception.ProductNotFound;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorException> handlerProductNotFound(ProductNotFound ex) {
        return buildException(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(DataIsIncorrect.class)
    public ResponseEntity<ErrorException> handlerDataIsIncorrect(DataIsIncorrect ex) {
        return buildException(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorException> handlerException(Exception exception){
        return  buildException(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorException> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException) {
        String errorMessage = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Проверьте корректность введенных данных");
        return buildException(HttpStatus.BAD_REQUEST, errorMessage);
    }

    private ResponseEntity<ErrorException> buildException(HttpStatus httpStatus, String message) {
        ErrorException errorException = new ErrorException(message);
        return ResponseEntity.status(httpStatus).body(errorException);
    }
}

