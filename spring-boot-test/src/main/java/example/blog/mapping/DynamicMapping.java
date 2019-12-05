package example.blog.mapping;

import org.springframework.aop.support.AopUtils;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liuyang
 * E-mail: liuyang06@agree.com.cn
 * 创建时间: 2019/12/2 11:03
 */
public class DynamicMapping extends RequestMappingHandlerMapping {
    final Object object;

    private final Map<String, HandlerMethod> handlerMethods = new LinkedHashMap<>();

    public DynamicMapping(Object object) {
        this.object = object;
        setOrder(Ordered.HIGHEST_PRECEDENCE + 520);
    }

    @Override
    protected void initHandlerMethods() {
        for (Method method : object.getClass().getMethods()) {
            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
            if (annotation != null) {
                String[] names = annotation.value();
                if(names == null) {
                    names = annotation.path();
                }
                if (names == null || names.length == 0) {
                    continue;
                }
                String name = names[0];
                if (!name.startsWith("/")) {
                    name = "/" + name;
                }
                HandlerMethod handlerMethod = createHandlerMethod(object, method);
                handlerMethods.put(name, handlerMethod);
            }
        }
    }

    public void init2() {
        RequestMappingInfo.Builder builder = RequestMappingInfo
                .paths(resolveEmbeddedValuesInPatterns(new String[]{"/config/add"}));
        RequestMappingInfo mapping = builder.build();
        for (Method method : object.getClass().getMethods()) {
            String name = method.getName();
            if (!name.contains("add")) {
                continue;
            }
            Method invocableMethod = AopUtils.selectInvocableMethod(method, object.getClass());
            registerHandlerMethod(object, invocableMethod, mapping);
        }
    }

    @Override
    protected HandlerMethod lookupHandlerMethod(String lookupPath, HttpServletRequest request) throws Exception {
        HandlerMethod handlerMethod = handlerMethods.get(lookupPath);
        if (handlerMethod != null) {
            return handlerMethod;
        }
        return super.lookupHandlerMethod(lookupPath, request);
    }
}
