# spring-boot

基于Gradle5.6.4的一个基础的SpringBoot工程，其他工程复制一份当前工程即可



# spring-boot-swagger

测试swagger功能的SpringBoot工程

## 依赖

```groovy
    // add swagger
    // https://mvnrepository.com/artifact/io.springfox/springfox-swagger2
    compile 'io.springfox:springfox-swagger2:2.9.2'
    // https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui
    compile 'io.springfox:springfox-swagger-ui:2.9.2'
```



## SwaggerConfig

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public SwaggerConfig() {
    }

    @Value(("${config.swagger-enable:true}"))
    private boolean swaggerEnable;

    @Value("${config.swagger-prefix:/basic}")
    private String swaggerPrefix;


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .select()
                //控制暴露出去的路径下的实例
                //如果某个接口不想暴露,可以使用以下注解
                //@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
//                .apis(RequestHandlerSelectors.basePackage(SpringBootSwaggerApplication.class.getPackage().getName()))
                .apis(RequestHandlerSelectors.withClassAnnotation(ExposeSwaggerApi.class))
                // 可以只暴露固定类下的固定方法，withMethodAnnotation需要与withClassAnnotation/basePackage共同使用
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ExposeSwaggerMethod.class))
                .paths(PathSelectors.any())
                .build()
                .enable(swaggerEnable);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot Swagger2 构建RESTful API")
                //条款地址
                .termsOfServiceUrl("https://mvnrepository.com/")
                .contact(new Contact("liu", "", ""))
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }

    public String getSwaggerPrefix() {
        return swaggerPrefix;
    }
}
```

## ExposeSwaggerApi

```java
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExposeSwaggerApi {
}
```

## ExposeSwaggerMethod

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExposeSwaggerMethod {
}
```

# spring-boot-test

测试部分SpringBoot工程的简单demo

## Bean加载顺序

### test.bean.init

测试结果：

```
bean constructor
调用postProcessBeforeInitialization...
调用Bean的函数(postConstruct)
bean initializing
调用Bean的函数(initMethod)
调用postProcessAfterInitialization...
// shutdown
调用Bean的函数(preDestroy)
bean disposable
调用Bean的函数(destroyMethod)
```



### Bean生命周期

<center>
<img style="border-radius: 0.3125em;
box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);" 
src="https://gitee.com/liu1204/images/raw/master/img/7qDql3.jpg">
<br>
<div style="color:orange; border-bottom: 1px solid #d9d9d9;
display: inline-block;
color: #999;
padding: 2px;">Bean的生命周期</div>
</center>

### 用法举例

#### BeanPostProcessor用法

注解、AOP等功能的实现均大量使用了 `BeanPostProcessor`，比如有一个自定义注解，你完全可以实现`BeanPostProcessor`的接口，在其中判断bean对象的脑袋上是否有该注解，如果有，你可以对这个bean实例执行任何操作

Aware接口：作用就是在对象实例化完成以后将Aware接口定义中规定的依赖注入到当前实例中。比如最常见的 `ApplicationContextAware`接口，实现了这个接口的类都可以获取到一个`ApplicationContext`对象。

当容器中每个对象的实例化过程走到`BeanPostProcessor`前置处理这一步时，容器会检测到之前注册到容器的ApplicationContextAwareProcessor，然后就会调用其`postProcessBeforeInitialization()`方法，检查并设置Aware相关依赖。代码如下：

```java
// 代码来自org.springframework.context.support.ApplicationContextAwareProcessor
// 其postProcessBeforeInitialization方法调用了invokeAwareInterfaces方法
private void invokeAwareInterfaces(Object bean) {
   if (bean instanceof EnvironmentAware) {
      ((EnvironmentAware) bean).setEnvironment(this.applicationContext.getEnvironment());
   }
   if (bean instanceof EmbeddedValueResolverAware) {
      ((EmbeddedValueResolverAware) bean).setEmbeddedValueResolver(this.embeddedValueResolver);
   }
   if (bean instanceof ResourceLoaderAware) {
      ((ResourceLoaderAware) bean).setResourceLoader(this.applicationContext);
   }
   if (bean instanceof ApplicationEventPublisherAware) {
      ((ApplicationEventPublisherAware) bean).setApplicationEventPublisher(this.applicationContext);
   }
   if (bean instanceof MessageSourceAware) {
      ((MessageSourceAware) bean).setMessageSource(this.applicationContext);
   }
   if (bean instanceof ApplicationContextAware) {
      ((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
   }
}
```

#### 自定义Aware

定义Aware接口：

```java
public interface CustomStringAware {		
    public void setCustomString(String customString);
}
```

实现BeanPostProcessor接口：
```java
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
        if(bean instanceof  CustomStringAware) {
            ((CustomStringAware)bean).setCustomString("String from PostProcessor!");
        }
        return bean;
    }
}
```

## 自动配置

### 常用注解

#### @Value

```java
@Value("value") 
private String name; 
```

value 的取值可以是：

- 字面量
- 通过 `${key}` 方式从环境变量中获取值
- 通过 `${key}` 方式全局配置文件中获取值
- `#{SpEL}`

所以，我们就可以通过 `@Value(${key})` 的方式获取全局配置文件中的指定配置项。

#### @ConfigurationProperties

标有 `@ConfigurationProperties` 的类的所有属性和配置文件中相关的配置项进行绑定。（默认从全局配置文件中获取配置值），绑定之后我们就可以通过这个类去访问全局配置文件中的属性值了。

下面看一个实例：

1.在主配置文件中添加如下配置

```properties
person.name=kundy 
person.age=13 
person.sex=male 
```

2.创建配置类，由于篇幅问题这里省略了 setter、getter 方法，但是实际开发中这个是必须的，否则无法成功注入。另外，@Component 这个注解也还是需要添加的。

```java
@Component 
@ConfigurationProperties(prefix = "person") 
public class Person { 

private String name; 
private Integer age; 
private String sex; 

} 
```

这里 `@ConfigurationProperties` 有一个 prefix 参数，主要是用来指定该配置项在配置文件中的前缀。

#### @Import 

##### **@Import 三种使用方式**

- 直接导入普通的 Java 类。
- 配合自定义的 ImportSelector 使用。
- 配合 ImportBeanDefinitionRegistrar 使用。

三种使用方式完整的Configuration类

```java
@Configuration
@Import(value = {Dog.class, CatImportSelector.class, PigImportBeanDefinitionRegistrar.class})
public class AppConfig {


}
```

##### 直接导入普通的java类

1. 声明Dog类

```java
public class Dog implements ISay {

    public void say() {
        System.out.println("汪汪");
    }
}
```

2. 添加到Config的Import中

##### 配合自定义的 ImportSelector 使用

1. 创建普通的Java类

```java
public class Cat implements ISay {
    @Override
    public void say() {
        System.out.println("喵喵喵！ ImportSelect!!");
    }
}
```

2. 创建ImportSelector实现类，并返回Cat的全类名

```java
public class CatImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"test.prop.autowired.entity.Cat"};
    }
}
```

3. 添加Selector到Config类中

##### 配合 ImportBeanDefinitionRegistrar 使用

1. 创建普通Pig类

```java
public class Pig implements ISay {

    @Override
    public void say() {
        System.out.println("Pig from registrar");
    }
}
```

2. 创建 ImportBeanDefinitionRegistrar 实现类，实现方法直接手动注册一个名叫 rectangle 的 Bean 到 IOC 容器中。

```java
public class PigImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Pig.class);
        registry.registerBeanDefinition("pig", rootBeanDefinition);
    }
}
```

3. 添加Registrar类到Config中

##### 编写测试

```java
Map<String, ISay> says = applicationContext.getBeansOfType(ISay.class);
says.values().forEach((say) -> {
    if (say != null) {
        say.say();
    }
});
```

输出结果为：

> 汪汪
> 喵喵喵！ ImportSelect!!
> Pig from registrar

### @Conditional

> @Conditional 注释可以实现只有在特定条件满足时才启用一些配置。

####  常用注解

| @Conditional派生注解            | 作用(都是判断是否符合指定的条件)               |
| :------------------------------ | :--------------------------------------------- |
| @ConditionalOnJava              | 系统的java版本是否符合要求                     |
| @ConditionalOnBean              | 有指定的Bean类                                 |
| @ConditionalOnMissingBean       | 没有指定的bean类                               |
| @ConditionalOnExpression        | 符合指定的SpEL表达式                           |
| @ConditionalOnClass             | 有指定的类                                     |
| @ConditionalOnMissingClass      | 没有指定的类                                   |
| @ConditionalOnSingleCandidate   | 容器只有一个指定的bean，或者这个bean是首选bean |
| @ConditionalOnProperty          | 指定的property属性有指定的值                   |
| @ConditionalOnResource          | 路径下存在指定的资源                           |
| @ConditionalOnWebApplication    | 系统环境是web环境                              |
| @ConditionalOnNotWebApplication | 系统环境不是web环境                            |
| @ConditionalOnjndi              | JNDI存在指定的项                               |

#### 自定义Conditional

##### 实现Condition接口

实现org.springframework.context.annotation.Condition接口的matcher方法，符合条件则返回true，否则则返回false。

```java
public class NotEmptyConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalNotEmptyProperty.class.getName());

        Environment environment = context.getEnvironment();
        // get key
        Object key = annotationAttributes.get("key");
        if (key instanceof String[]) {
            String[] keys = (String[]) key;
            for (String one : keys) {
                String property = environment.getProperty(one);
                if (property == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

##### 自定义Conditional注解

注解需要添加@Conditional，指定Condition实现类

```java
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Conditional(NotEmptyConditional.class)
public @interface ConditionalNotEmptyProperty {
    String[] key() default {};
}
```

##### 测试

```java
@Configuration
@ConditionalNotEmptyProperty(key = {"abc"})
public class NotEmptyConfiguration {
    public NotEmptyConfiguration() {
        System.err.println("NotEmptyConfiguration Constructor!!");
    }
}
```





