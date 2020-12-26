package org.houke.demo.springboot.aop.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Program :        houke-practice-demo
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-18  23:53
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER, ElementType.FIELD})
public @interface Simple {
    String name() default "";
    int value() default 1;
}
