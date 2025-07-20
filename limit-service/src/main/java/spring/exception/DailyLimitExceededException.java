package spring.exception;

import java.math.BigDecimal;

public class DailyLimitExceededException extends RuntimeException {
    public DailyLimitExceededException(BigDecimal remainingLimit) {
        super("Операция превышает доступный дневной лимит. Остаток лимита: " + remainingLimit);
    }
}
