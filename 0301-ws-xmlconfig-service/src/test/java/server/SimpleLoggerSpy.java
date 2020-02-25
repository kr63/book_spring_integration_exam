package server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.spy;

@Configuration
public class SimpleLoggerSpy {
    @Bean
    @Primary
    public SimpleLogger registersimpleLoggerSpy() {
        return spy(new SimpleLogger());
    }
}
