package spring.dto;

import spring.enums.Direction;

import java.math.BigDecimal;

public record RequestPaymentDTO(
        Long productId,
        Direction direction,
        BigDecimal amount
) {}
