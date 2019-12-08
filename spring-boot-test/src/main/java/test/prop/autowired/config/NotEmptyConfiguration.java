package test.prop.autowired.config;

import org.springframework.context.annotation.Configuration;
import test.prop.autowired.condition.ConditionalNotEmptyProperty;

@Configuration
@ConditionalNotEmptyProperty(key = {"abc"})
public class NotEmptyConfiguration {
    public NotEmptyConfiguration() {
        System.err.println("NotEmptyConfiguration Constructor!!");
    }
}
