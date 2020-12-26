package com.houke.demo.lamda.basic.transversal;

import com.houke.demo.lamda.BasicTest;
import com.houke.demo.lamda.basic.transversal.base.FourTypeTransversalDemo1;
import com.houke.demo.lamda.basic.transversal.base.FourTypeTransversalDemo2;
import com.houke.demo.lamda.basic.transversal.base.FourTypeTransversalDemo3;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program :        houke-test-demo
 * @Author :         houke_zou
 * @date :           2020-12-26  15:25
 * @description :    参数可变, 详细结果可参考readme
 */
public class BaseTransversalUnitTest extends BasicTest {

    @Autowired
    FourTypeTransversalDemo1 demo1;
    @Autowired
    FourTypeTransversalDemo2 demo2;
    @Autowired
    FourTypeTransversalDemo3 demo3;

    @Test
    public void test100() {
        Long num = 10000000L;
        demo1.transversal(num);
        System.out.println(" --- 分割线 ---");
        demo2.transversal(num);
        System.out.println(" --- 分割线 ---");
        demo3.transversal(num);
    }

    @Test
    public void test1000() {
        Long num = 100000000L;
        demo1.transversal(num);
        System.out.println(" --- 分割线 ---");
        demo2.transversal(num);
        System.out.println(" --- 分割线 ---");
        demo3.transversal(num);
    }
}
