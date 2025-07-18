package spring.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.entity.Product;
import spring.entity.User;
import spring.enums.ProductType;
import spring.repository.ProductRepository;
import spring.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;

    @Transactional
    public Product create(String accountNumber, BigDecimal balance,
                          ProductType type, Long userId) {
        User user = userService.getById(userId);

        Product product = new Product();
        product.setAccountNumber(accountNumber);
        product.setBalance(balance);
        product.setType(type);
        product.setUser(user);
        return productRepository.save(product);
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }

    public List<Product> getAllByUserId(Long userId) {
        return productRepository.findByUserId(userId);
    }
}

