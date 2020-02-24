package test.prop.autowired.entity;

/**
 * 通过SpringFactoriesLoader加载，定义见resource/META-INF/spring.factories
 */
public class Factory implements ISay {
    @Override
    public void say() {
        System.err.println("factory load by spring.factories");
    }
}
