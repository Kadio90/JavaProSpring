package spring.configuration.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "product-service")
public class ProductClientProperties {
    private final String baseUrl;
    private final String productByIdUri;
    private final String productsByUserUri;
}
