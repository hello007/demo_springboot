package test.bean.init;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class User implements InitializingBean, DisposableBean, CustomStringAware {
    public User() {
        System.out.println("bean constructor");
    }

    public void initMethod() {
        System.out.println("调用Bean的函数(initMethod)");
    }

    public void destroyMethod() {
        System.out.println("调用Bean的函数(destroyMethod)");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("调用Bean的函数(postConstruct)");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("调用Bean的函数(preDestroy)");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("bean initializing");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("bean disposable");
    }

    @Override
    public void setCustomString(String customString) {
        System.err.println("User:" + customString);
    }
}
