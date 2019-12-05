package example.blog.annotaion;

import java.lang.annotation.*;

/**
 * @author liuyang
 * E-mail: liuyang06@agree.com.cn
 * 创建时间: 2019/12/2 14:48
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DynamicMethod {
    String value();

    String description() default "";
}
