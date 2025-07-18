package spring.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.dto.RequestPaymentDTO;
import spring.dto.ResponsePaymentDTO;
import spring.dto.ResponseProductDTO;
import spring.entity.Transaction;
import spring.enums.Direction;
import spring.enums.PaymentStatus;
import spring.enums.TransactionStatus;
import spring.exception.InsufficientFundsException;
import spring.repository.TransactionRepository;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentService {
    private final ProductClientService productClientService;
    private final TransactionRepository transactionRepository;

    @Transactional
    private Transaction create(Long productId, Direction direction, BigDecimal amount){
        Transaction transaction = new Transaction();
        transaction.setProductId(productId);
        transaction.setDirection(direction);
        transaction.setAmount(amount);
        transaction.setStatus(TransactionStatus.CREATED);
        transactionRepository.save(transaction);
        return transaction;
    }

    public ResponsePaymentDTO processPayment(RequestPaymentDTO request) {
            // 1. Получаем продукт
            log.info("Запрос для сервиса с продуктом : {}, сумма: {}",request.productId(), request.amount());
            ResponseProductDTO product = productClientService.getProductById(request.productId());
            log.info("Найден продукт: {}", product.toString());
            // 2. Создаем новую транзакцию
            Transaction transaction = create(request.productId(), request.direction(), request.amount());

            // 3. Если транзакция "DT", то Проверяем баланс продукта в случае недостатка средств помечаем транзакцию как ошибочную
            if (request.direction().equals(Direction.DT) && product.getBalance().compareTo(request.amount()) < 0) {
                transaction.setStatus(TransactionStatus.FAILED);
                transactionRepository.save(transaction);
                throw new InsufficientFundsException();
            }

            // 3. Фиксируем транзакцию и посылаем в продукт информацию об обновлении баланса счета и
            // Отправку PUT запроса на обновление информации по изменению баланса по продукту было бы не плохо реализовать
            transaction.setStatus(TransactionStatus.COMPLETED);
            transactionRepository.save(transaction);
            String message = "Баланс продукта после выполнения транзакции: " + transactionRepository.calculateBalanceByProductId(request.productId()).toString();
            return new ResponsePaymentDTO(transaction.getId().toString(), PaymentStatus.SUCCESS, message);
    }
}

