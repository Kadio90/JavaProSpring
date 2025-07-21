package spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Clock;

@Configuration
@EnableScheduling
public class ShedulerConfuguration {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
