package spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.dto.ResponseLimitDTO;
import spring.mapper.LimitMapper;
import spring.service.LimitService;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/api/v1/limits")
@RequiredArgsConstructor
public class LimitController {
    private final LimitService limitService;
    private final LimitMapper limitMapper;

    // Запрашиваем суточный лимит пользователя
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseLimitDTO getRemainingDailyLimit(@PathVariable(name = "userId") Long userId) {
        return limitMapper.fromEntity(limitService.getUserDailyLimit(userId), "Доступный лимит");
    }

    // Резервируем лимит
    @PutMapping("/decrease")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseLimitDTO decreaseLimit(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "amount") BigDecimal amount) {
        return limitMapper.fromEntity(limitService.decreaseLimit(userId, amount), "Лимит успешно зарезервирован");
    }

    // Восстанавливаем лимит в случае отката операции
    @PutMapping("/increase")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseLimitDTO increaseLimit(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "amount") BigDecimal amount) {
        return limitMapper.fromEntity(limitService.increaseLimit(userId, amount), "Лимит успешно восстановлен");
    }
}