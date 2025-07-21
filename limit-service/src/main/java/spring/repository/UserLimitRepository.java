package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.entity.UserLimit;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UserLimitRepository extends JpaRepository<UserLimit, Long> {
    Optional<UserLimit> findByUserId(Long userId);

    @Modifying
    @Query("UPDATE UserLimit ul SET ul.remainingDailyLimit = :limitValue, ul.limitDate = :currentDate")
    int resetAllDailyLimits(@Param("currentDate") LocalDate currentDate, @Param("limitValue")BigDecimal limitValue);
}
