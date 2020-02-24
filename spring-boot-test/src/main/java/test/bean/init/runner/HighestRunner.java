package test.bean.init.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HighestRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.err.println("Ordered.HIGHEST_PRECEDENCE: " + (Ordered.HIGHEST_PRECEDENCE));
    }
}
