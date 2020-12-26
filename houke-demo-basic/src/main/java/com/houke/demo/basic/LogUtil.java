package com.houke.demo.basic;

/**
 * @program :        houke-test-demo
 * @Author :         houke_zou
 * @date :           2020-12-26  14:44
 * @description :
 */
public class LogUtil {
    public static void log(Object a) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        System.out.println(element.getClassName() + " - " + element.getMethodName() + " -  : " + a.toString());
    }
}
