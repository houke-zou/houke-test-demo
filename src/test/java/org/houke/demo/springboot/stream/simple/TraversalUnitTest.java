package org.houke.demo.springboot.stream.simple;

import org.houke.demo.springboot.JavaBaseTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program :        springboot-4test
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-25  21:14
 */
public abstract class TraversalUnitTest extends JavaBaseTest {

    public List<DemoTestBean> createOriginalList(Long num) {
        long start = System.currentTimeMillis();
        List<DemoTestBean> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer result = (int) (Math.random() * 100 + 1);
            list.add(new DemoTestBean(1, "a", "b", "c", "d", "e", "f", result));
        }
        long end = System.currentTimeMillis();
        log("the cost of create srcList is " + (end - start));
        return list;
    }

    public Long traversalEnhanceFor(List<DemoTestBean> list) {
        long start = System.currentTimeMillis();
        for (DemoTestBean a : list) {
            doSomething(a);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public Long traversalSimpleFor(List<DemoTestBean> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            doSomething(list.get(i));
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public Long traversalStream(List<DemoTestBean> list) {
        long start = System.currentTimeMillis();
        list.stream().forEach(this::doSomething);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public Long traversalParallelStream(List<DemoTestBean> list) {
        long start = System.currentTimeMillis();
        list.parallelStream().forEach(this::doSomething);
        long end = System.currentTimeMillis();
        return end - start;
    }

    abstract void doSomething(DemoTestBean bean);
}
