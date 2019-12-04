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

