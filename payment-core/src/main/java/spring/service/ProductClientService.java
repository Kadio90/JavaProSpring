package spring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import spring.configuration.property.ProductClientProperties;
import spring.dto.ResponseProductDTO;
import spring.exception.IntegrationException;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductClientService {
    private final RestClient restClient;
    private final ProductClientProperties integrationClientProperties;

    public ResponseProductDTO getProductById(Long id) {
        log.info("Запрос к сервису getProductById: {}",integrationClientProperties.getProductByIdUri());
        return restClient.get()
                .uri(integrationClientProperties.getProductByIdUri(), id)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, (req, res) -> {
                    throw new IntegrationException("Продукт не найден во внешнем сервисе");
                })
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new IntegrationException("Ошибка при обращении к внешнему сервису: " + res.getStatusCode());
                })
                .body(ResponseProductDTO.class);
    }

    public List<ResponseProductDTO> getUserProducts(Long userId) {
        log.info("Запрос к сервису getUserProducts: {}",integrationClientProperties.getProductsByUserUri());
        return restClient.get()
                .uri(integrationClientProperties.getProductsByUserUri(), userId)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new IntegrationException("Ошибка при обращении к внешнему сервису: " + res.getStatusCode());
                })
                .body(new ParameterizedTypeReference<List<ResponseProductDTO>>() {});
    }
}
