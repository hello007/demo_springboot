package test.bean.init;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == User.class) {
            System.out.println("调用postProcessBeforeInitialization...");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == User.class) {
            System.err.println("调用postProcessAfterInitialization...");
        }
        if(bean instanceof  CustomStringAware) {
            ((CustomStringAware)bean).setCustomString("String from PostProcessor!");
        }
        return bean;
    }
}
