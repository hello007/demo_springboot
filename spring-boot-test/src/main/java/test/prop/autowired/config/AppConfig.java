package test.prop.autowired.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import test.prop.autowired.entity.Dog;
import test.prop.autowired.registrar.PigImportBeanDefinitionRegistrar;
import test.prop.autowired.selector.CatImportSelector;

@Configuration
@Import(value = {Dog.class, CatImportSelector.class, PigImportBeanDefinitionRegistrar.class})
public class AppConfig {


}
