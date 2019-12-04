package example.swagger.config;

import example.swagger.annotation.ExposeSwaggerApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
