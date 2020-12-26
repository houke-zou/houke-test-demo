package org.houke.demo.springboot.service;

import org.houke.demo.springboot.bean.FirstDemoBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Program :        springboot-4test
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-19  17:48
 */
@SpringBootTest
public class DomainServiceUnitTest {
    @Autowired private DomainService domainService;

    private FirstDemoBean bean = new FirstDemoBean("key", "backup");


    @Test
    public void test1() {
        domainService.doSomething(bean, "first", "second", 3);
    }
}
