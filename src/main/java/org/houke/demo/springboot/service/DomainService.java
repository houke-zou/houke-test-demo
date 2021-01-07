package org.houke.demo.springboot.service;

import org.houke.demo.springboot.aop.basic.Simple;
import org.houke.demo.springboot.aop.basic.SimpleAopHandler;
import org.houke.demo.springboot.aop.basic.SimpleAopHandler2;
import org.houke.demo.springboot.bean.FirstDemoBean;
import org.springframework.stereotype.Component;

/**
 * @Program :        springboot-4test
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-19  17:47
 */
@Component
public class DomainService {

//    private FirstDemoBean bean = new FirstDemoBean("key", "backup");

//    @SimpleAopHandler
    @SimpleAopHandler2
    public void doSomething(FirstDemoBean bean, @Simple() String a, String b, @Simple Integer c) {
        System.out.println("---- 正式内容 ---- 开始 ----");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println("do Something");
        System.out.println("---- 正式内容 ---- 结束 ----");
        throw new RuntimeException();
    }
}
