package com.houke.demo.lamda.basic.transversal;

import com.houke.demo.lamda.BasicTest;
import com.houke.demo.lamda.basic.transversal.lazy.LazyLoadingDemo1;
import com.houke.demo.lamda.basic.transversal.lazy.LazyLoadingDemo2;
import com.houke.demo.lamda.basic.transversal.lazy.LazyLoadingDemo3;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program :        houke-test-demo
 * @Author :         houke_zou
 * @date :           2020-12-26  16:33
 * @description :
 */
public class LazyLoadingUnitTest extends BasicTest {

    @Autowired
    LazyLoadingDemo1 demo1;     // 正常加载
    @Autowired
    LazyLoadingDemo2 demo2;     // 懒加载
    @Autowired
    LazyLoadingDemo3 demo3;     // 正常加载 & demo3 == demo2

    /**
     * 懒加载对 parallelStream有影响
     * 加载完成后 parallelStream恢复
     */
    @Test
    public void simple() {
        Long num = 10000000L;
        demo1.transversal(num);
        System.out.println(" --- 分割线 ---");
        demo1.transversal(num);
    }

    @Test
    public void lazy() {
        Long num = 10000000L;
        demo2.transversal(num);
        System.out.println(" --- 分割线 ---");
        demo2.transversal(num);
    }

    @Test
    public void forCompare() {
        Long num = 10000000L;
        demo2.transversal(num);
        System.out.println(" --- 分割线 ---");
        demo3.transversal(num);
    }

    @Test
    public void forCompareReverse() {
        Long num = 10000000L;
        demo3.transversal(num);
        System.out.println(" --- 分割线 ---");
        demo2.transversal(num);
    }
}
