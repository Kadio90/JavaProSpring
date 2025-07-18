package spring.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.dto.RequestPaymentDTO;
import spring.dto.ResponsePaymentDTO;
import spring.dto.ResponseProductDTO;
import spring.service.PaymentService;
import spring.service.ProductClientService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final ProductClientService productClient;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponsePaymentDTO processPayment(@RequestBody RequestPaymentDTO request) {
        return paymentService.processPayment(request);
    }

    @GetMapping("/products/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseProductDTO> getUserProducts(@PathVariable Long userId) {
        return productClient.getUserProducts(userId);
    }
}
