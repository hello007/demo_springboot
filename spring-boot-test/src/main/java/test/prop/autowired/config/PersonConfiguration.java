package test.prop.autowired.config;

import org.springframework.context.annotation.Configuration;
import test.prop.autowired.condition.ConditionalNotEmptyProperty;

@Configuration
@ConditionalNotEmptyProperty(key = {"person.name"})
public class PersonConfiguration {
    public PersonConfiguration() {
        System.err.println("PersonConfiguration Constructor!!");
    }
}
