package ru.t1.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.t1.spring.enums.ProductType;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Product{id=" + id + ", accountNumber='" + accountNumber +
                "', balance=" + balance + ", type=" + type +
                ", userId=" + (user != null ? user.getId() : null) + "}";
    }
}
