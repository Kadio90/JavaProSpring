package ru.t1.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.t1.spring.dto.ProductCreateRequestDTO;
import ru.t1.spring.dto.ProductResponceDTO;
import ru.t1.spring.mapper.ProductMapper;
import ru.t1.spring.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    public ProductResponceDTO getProduct(@PathVariable(name = "id") Long id) {
        return productMapper.fromEntity(productService.getById(id));

    }

    @GetMapping("/user/{userId}")
    public List<ProductResponceDTO> getProductsByUser(@PathVariable(name = "userId") Long userId) {
        return productService.getAllByUserId(userId).stream()
                .map(productMapper::fromEntity)
                .toList();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponceDTO createProduct(@RequestBody ProductCreateRequestDTO request) {
        return productMapper.fromEntity(
                productService.create(
                    request.getAccountNumber(),
                    request.getBalance(),
                    request.getType(),
                    request.getUserId()
                )
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
    }
}