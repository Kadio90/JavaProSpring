package spring.configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import spring.configuration.property.ProductClientProperties;

@Slf4j
@EnableConfigurationProperties(ProductClientProperties.class)
@Configuration
public class RestClientConfiguration {

    @Bean
    public RestClient productServiceClient(ProductClientProperties integrationClientProperties) {
        return RestClient.builder()
                .baseUrl(integrationClientProperties.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}

