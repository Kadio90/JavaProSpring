package spring.dto;

import lombok.Getter;
import spring.enums.PaymentStatus;

import java.time.LocalDateTime;

@Getter
public class ResponsePaymentDTO {
    public String transactionId;
    public PaymentStatus status;
    public String message;
    public LocalDateTime timestamp;

    public ResponsePaymentDTO(String transactionId, PaymentStatus status, String message) {
        this.transactionId = transactionId;
        this.status = status;
        this.message = message;
        timestamp = LocalDateTime.now();
    }
}
