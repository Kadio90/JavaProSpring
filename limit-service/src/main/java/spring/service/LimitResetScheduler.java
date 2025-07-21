package spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import spring.configuration.properties.LimitProperties;
import spring.repository.UserLimitRepository;

import java.time.Clock;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class LimitResetScheduler {
    private final UserLimitRepository userLimitRepository;
    private final LimitProperties limitProperties;
    private final Clock clock;

    @Scheduled(cron = "${limits.reset-cron}") // Каждый день в 00:00
    @Transactional
    public void resetDailyLimits() {
        try {
            LocalDate today = LocalDate.now(clock);
            log.info("Старт сброса суточных лимитов в {}", today);
            int updated = userLimitRepository.resetAllDailyLimits(today, limitProperties.getDefaultDailyLimit());
            log.info("Успешный сброс суточных лимитов для {} пользователей", updated);
        } catch (Exception ex) {
            log.error("Ошибка сброса суточных лимитов", ex);
        }
    }
}
