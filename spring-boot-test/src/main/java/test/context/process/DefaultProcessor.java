package test.context.process;

import org.springframework.stereotype.Component;

@Component(value = "custom")
public class DefaultProcessor implements  IProcessor {
    @Override
    public void process() {

    }
}
