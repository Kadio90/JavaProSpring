package spring.mapper;

import org.springframework.stereotype.Component;
import spring.dto.ProductResponceDTO;
import spring.entity.Product;

@Component
public class ProductMapper {

    public ProductResponceDTO fromEntity(Product product) {
        return new ProductResponceDTO(
                product.getUser().getUsername(),
                product.getAccountNumber(),
                product.getBalance(),
                product.getType()
        );
    }
}
