package spring.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseErrorDTO {
    private final String code;
    private final String message;
    private final LocalDateTime timestamp;

    public ResponseErrorDTO(String code, String message) {
        this.code = code;
        this.message = message;
        timestamp = LocalDateTime.now();
    }
}

