package spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.enums.ProductType;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class ResponseProductDTO {
    private String userName;
    private String accountNumber;
    private BigDecimal balance;
    private ProductType type;
}
