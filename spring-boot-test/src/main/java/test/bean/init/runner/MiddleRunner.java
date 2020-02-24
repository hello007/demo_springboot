package test.bean.init.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class MiddleRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.err.println("Ordered.(Ordered.LOWEST_PRECEDENCE - 100):" + (Ordered.LOWEST_PRECEDENCE - 100));
    }
}
