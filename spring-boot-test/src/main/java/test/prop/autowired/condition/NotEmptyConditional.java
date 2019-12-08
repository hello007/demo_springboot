package test.prop.autowired.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

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
