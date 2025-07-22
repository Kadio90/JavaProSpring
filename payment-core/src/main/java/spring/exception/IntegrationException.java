package spring.exception;

import lombok.Getter;

@Getter
public class IntegrationException extends RuntimeException {
    private final String externalMessage;

    public IntegrationException(String externalMessage) {
        super("Ошибка при вызове внешнего сервиса");
        this.externalMessage = externalMessage;
    }
}
