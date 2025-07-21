package spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.configuration.properties.LimitProperties;
import spring.entity.UserLimit;
import spring.exception.DailyLimitExceededException;
import spring.repository.UserLimitRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class LimitService {
    private final UserLimitRepository userLimitRepository;
    private final LimitProperties limitProperties;

    //Создание записи о лимите клиента
    public UserLimit createUserLimit(Long userId) {
            UserLimit limit = new UserLimit();
            limit.setUserId(userId);
            limit.setRemainingDailyLimit(limitProperties.getDefaultDailyLimit());
            limit.setLimitDate(LocalDate.now());
            return userLimitRepository.save(limit);
    }

    // Получение записи по суточному лимиту клиента либо если нет создаем новую запись
    public UserLimit getUserDailyLimit(Long userId) {
        return userLimitRepository.findByUserId(userId)
                .orElseGet(() -> createUserLimit(userId));
    }

    // Перед платежом резервируем лимит под него
    public UserLimit decreaseLimit(Long userId, BigDecimal amount) {
        UserLimit userDailyLimit = getUserDailyLimit(userId);
        BigDecimal minusValue = userDailyLimit.getRemainingDailyLimit().subtract(amount);
        if (minusValue.compareTo(BigDecimal.valueOf(0)) < 0) {
            //В случае превышения суточного лимита кидаем исключение
            throw new DailyLimitExceededException(userDailyLimit.getRemainingDailyLimit());
        }
        userDailyLimit.setRemainingDailyLimit(minusValue);
        userLimitRepository.save(userDailyLimit);
        return userDailyLimit;
    }

    // В случае отката платежа возвращаем зарезервированный лимит обратно
    public UserLimit increaseLimit(Long userId, BigDecimal amount) {
        UserLimit userDailyLimit = getUserDailyLimit(userId);

        BigDecimal sumValue = userDailyLimit.getRemainingDailyLimit().add(amount);
        if (sumValue.compareTo(limitProperties.getDefaultDailyLimit()) > 0) {
            userDailyLimit.setRemainingDailyLimit(limitProperties.getDefaultDailyLimit());
        } else {
            userDailyLimit.setRemainingDailyLimit(sumValue);
        }
        userLimitRepository.save(userDailyLimit);
        return userDailyLimit;
    }
}
