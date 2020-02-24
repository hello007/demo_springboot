package test.prop.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.stereotype.Component;
import test.prop.autowired.entity.Dog;
import test.prop.autowired.entity.ISay;

import java.util.List;
import java.util.Map;

@Component
public class TestCommandLineRunner implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        Person person = applicationContext.getBean(Person.class);
        doSay(person);

        Dog dog = applicationContext.getBean(Dog.class);
        doSay(dog);

        System.err.println("-----------------------------");
        Map<String, ISay> says = applicationContext.getBeansOfType(ISay.class);
        says.values().forEach((say) -> {
            doSay(say);
        });

        // 获取spring.factories中定义的ISay
        List<ISay> iSays = SpringFactoriesLoader.loadFactories(ISay.class, null);
        iSays.forEach((say) -> {
            if (say != null) {
                say.say();
            }
        });
    }

    public void doSay(ISay say) {
        if (say != null) {
            say.say();
        }
    }
}
