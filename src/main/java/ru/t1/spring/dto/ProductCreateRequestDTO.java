package ru.t1.spring.dto;

import lombok.Getter;
import lombok.Setter;
import ru.t1.spring.enums.ProductType;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductCreateRequestDTO {
    private String accountNumber;
    private BigDecimal balance;
    private ProductType type;
    private Long userId;
}
