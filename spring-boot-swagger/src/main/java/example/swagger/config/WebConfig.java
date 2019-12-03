package example.swagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private String prefix = "/basic";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(prefix + "/**")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController(prefix + "/v2/api-docs", "/v2/api-docs")
                .setKeepQueryParams(true);
        registry.addRedirectViewController(prefix + "/swagger-resources/configuration/ui",
                "/swagger-resources/configuration/ui");
        registry.addRedirectViewController(
                prefix + "/swagger-resources/configuration/security",
                "/swagger-resources/configuration/security");
        registry.addRedirectViewController(prefix + "/swagger-resources",
                "/swagger-resources");
        registry.addRedirectViewController(prefix + "/csrf", "/csrf")
                .setKeepQueryParams(true);
    }
}
