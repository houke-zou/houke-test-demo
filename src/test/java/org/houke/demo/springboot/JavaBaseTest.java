package org.houke.demo.springboot;

/**
 * @Program :        springboot-4test
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-23  20:42
 */
public class JavaBaseTest {
    protected void log(Object a) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        System.out.println(element.getClassName() + " - " + element.getMethodName() + " -  : " + a.toString());
    }
}
