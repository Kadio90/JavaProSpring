package spring.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.dto.ResponseErrorDTO;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseErrorDTO handleException(ProductNotFoundException ex) {
        return new ResponseErrorDTO(HttpStatus.NOT_FOUND.name(), ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseErrorDTO handleException(RuntimeException ex) {
        return new ResponseErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.name(), ex.getMessage());
    }
}
