package spring.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.dto.ResponseErrorDTO;
import spring.enums.PaymentStatus;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseErrorDTO handleException(InsufficientFundsException ex) {
        return new ResponseErrorDTO(PaymentStatus.INSUFFICIENT_FUNDS.toString(), ex.getMessage());
    }

    @ExceptionHandler(IntegrationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseErrorDTO handleException(IntegrationException ex) {
        return new ResponseErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage() + ": " + ex.getExternalMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseErrorDTO handleException(RuntimeException ex) {
        return new ResponseErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.name(), ex.getMessage());
    }
}
