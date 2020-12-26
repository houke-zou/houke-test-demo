package com.houke.demo.lamda.basic.transversal.base;

import com.houke.demo.basic.LogUtil;
import com.houke.demo.lamda.basic.transversal.Transversal;
import com.houke.demo.lamda.domain.Person;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program :        houke-test-demo
 * @Author :         houke_zou
 * @date :           2020-12-26  15:45
 * @description :
 */
@Lazy
@Component
public class FourTypeTransversalDemo2 extends Transversal {
    /**
     * 注: 五次平均值, 单位(ms)
     * <p>
     * 循环次数         1000000     10000000    50000000
     * 增强for循环      44          445         2117
     * 普通for循环      41          509         2437
     * 串行stream      41          474         2299
     * 并行stream      38          224         1057
     */
    private int a = 13;

    public void transversal(Long num) {
        long start = System.currentTimeMillis();
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer result = (int) (Math.random() * 100 + 1);
            list.add(new Person(1L, "a", "b", "c", "d", "e", "f", result));
        }
        long end = System.currentTimeMillis();
        LogUtil.log("the create time is :" + (end - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            for (Person person : list) {
                doSomething(person);
            }
        }
        end = System.currentTimeMillis();
        LogUtil.log("the output of enhance for loop time is :" + (end - start));
        start = System.currentTimeMillis();
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < list.size(); i++) {
                doSomething(list.get(i));
            }
        }
        end = System.currentTimeMillis();
        LogUtil.log("the output of simple for loop time is :" + (end - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            list.stream().forEach(this::doSomething);
        }
        end = System.currentTimeMillis();
        LogUtil.log("the output of stream for loop time is :" + (end - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            list.parallelStream().forEach(this::doSomething);
        }
        end = System.currentTimeMillis();
        LogUtil.log("the output of parallel stream for loop time is :" + (end - start));
    }

    @Override
    public void doSomething(Person person) {
        super.doSomething(person);
        person.setRandom(a);
    }
}
