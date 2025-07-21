package spring.configuration.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "limits")
public class LimitProperties {
    private final BigDecimal defaultDailyLimit;
}
