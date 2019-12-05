package example.blog.mapping;

import example.blog.MappingManager;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author liuyang
 * E-mail: liuyang06@agree.com.cn
 * 创建时间: 2019/12/2 14:28
 */
public class RequestMappingBuild extends RequestMappingHandlerMapping {

    @Autowired
    private MappingManager mappingManager;

    public void build() {
        RequestMappingInfo.Builder builder = RequestMappingInfo
                .paths(resolveEmbeddedValuesInPatterns(new String[]{"/config/add"}));
        RequestMappingInfo mapping = builder.build();
        for (Method method : mappingManager.getClass().getMethods()) {
            Method invocableMethod = AopUtils.selectInvocableMethod(method, mappingManager.getClass());
            registerHandlerMethod(mappingManager, invocableMethod, mapping);
        }

    }
}
