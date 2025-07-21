package spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ResponseLimitDTO {
    private Long userId;
    private BigDecimal limit;
    private String message;
}
