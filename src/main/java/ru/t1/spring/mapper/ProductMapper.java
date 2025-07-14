package ru.t1.spring.mapper;

import org.springframework.stereotype.Component;
import ru.t1.spring.dto.ProductResponceDTO;
import ru.t1.spring.entity.Product;

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
