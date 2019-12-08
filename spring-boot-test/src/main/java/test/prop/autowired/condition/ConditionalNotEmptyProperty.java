package test.prop.autowired.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Conditional(NotEmptyConditional.class)
public @interface ConditionalNotEmptyProperty {
    String[] key() default {};
}
