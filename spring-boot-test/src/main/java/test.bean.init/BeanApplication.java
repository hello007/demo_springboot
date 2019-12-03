package test.bean.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BeanApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeanApplication.class, args);
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public User user() {
        return new User();
    }
}
