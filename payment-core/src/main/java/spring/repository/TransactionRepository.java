package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.entity.Transaction;

import java.math.BigDecimal;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT COALESCE(SUM(CASE WHEN t.direction = 'KT' THEN t.amount ELSE -t.amount END), 0) " +
            "FROM Transaction t " +
            "WHERE t.productId = :productId " +
            "and t.status = 'COMPLETED'")
    BigDecimal calculateBalanceByProductId(@Param("productId") Long productId);

}



