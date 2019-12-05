package example.blog.mapping;

import example.blog.MappingManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuyang
 * E-mail: liuyang06@agree.com.cn
 * 创建时间: 2019/12/2 11:11
 */
@Configuration
public class DynamicConfig {

    @Bean
    public DynamicMapping getMapping() {
        return new DynamicMapping(new MappingManager());
    }
}
