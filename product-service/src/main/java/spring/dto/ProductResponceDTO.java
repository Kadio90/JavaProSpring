package spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spring.enums.ProductType;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductResponceDTO {
    private String userName;
    private String accountNumber;
    private BigDecimal balance;
    private ProductType type;
}
