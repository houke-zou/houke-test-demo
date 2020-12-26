package org.houke.demo.springboot.aop;

/**
 * @Program :        springboot-4test
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-25  16:25
 */
public @interface Cache {
    int time() default 0;
}
