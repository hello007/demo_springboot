package test.bean.init.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class LowestRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.err.println("Ordered.LOWEST_PRECEDENCE :" + Ordered.LOWEST_PRECEDENCE);

        List<Integer> nums = Arrays.asList(3, 2, 5, 8, 10, 6);
        Collections.sort(nums, Integer::compareTo);
        System.out.println(nums);

        Collections.sort(nums, (x, y) -> {
            if (x > y) {
                return -1;
            } else if (x == y) {
                return 0;
            }
            return 1;
        });
        System.err.println(nums);

    }
}
