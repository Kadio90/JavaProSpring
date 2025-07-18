package spring.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Не достаточно средств для исполнения операции");
    }
}
