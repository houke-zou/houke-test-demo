package org.houke.demo.springboot.stream.simple;

import org.houke.demo.springboot.JavaBaseTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program :        springboot-4test
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-19  21:27
 */
public class FourTypeTraversalUnitTest2 extends JavaBaseTest {
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

    @Test
    public void transversal() {
        long start = System.currentTimeMillis();
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 5000000; i++) {
            Integer result = (int) (Math.random() * 100 + 1);
            list.add(new Person(1, "a", "b", "c", "d", "e", "f", result));
        }
        long end = System.currentTimeMillis();
        log("the create time is :" + (end - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            for (Person person : list) {
                doSomething(person);
            }
        }
        end = System.currentTimeMillis();
        log("the output of enhance for loop time is :" + (end - start));
        start = System.currentTimeMillis();
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < list.size(); i++) {
                doSomething(list.get(i));
            }
        }
        end = System.currentTimeMillis();
        log("the output of simple for loop time is :" + (end - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            list.stream().forEach(this::doSomething);
        }
        end = System.currentTimeMillis();
        log("the output of stream for loop time is :" + (end - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            list.parallelStream().forEach(this::doSomething);
        }
        end = System.currentTimeMillis();
        log("the output of parallel stream for loop time is :" + (end - start));
    }

    public void doSomething(Person person) {
        String temp;
        temp = person.getA();
        person.setA(person.getB());
        person.setB(person.getC());
        person.setC(person.getD());
        person.setD(person.getE());
        person.setE(person.getF());
        person.setF(temp);
        person.setRandom(a);
    }
}
