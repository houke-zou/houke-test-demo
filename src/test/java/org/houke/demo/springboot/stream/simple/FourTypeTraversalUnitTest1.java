package org.houke.demo.springboot.stream.simple;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @Program :        springboot-4test
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-19  21:27
 */
public class FourTypeTraversalUnitTest1 extends TraversalUnitTest {

    /**
     * 注: 五次总和, 单位(ms)
     *
     * 循环次数         100      1000     10000     100000      1000000     10000000    50000000
     * 增强for循环      1        3        6         10          43          447         2032
     * 普通for循环      0        0        3         9           37          360         1786
     * 串行stream      0        2        1         5           35          454         2045
     * 并行stream      4        3        5         15          34          204         1188
     */

    private int a = 13;

    @Test
    public void transversal() {
        List<Person> list = createOriginalList(10000000L);
        List<Long> res1 = new ArrayList<>();
        List<Long> res2 = new ArrayList<>();
        List<Long> res3 = new ArrayList<>();
        List<Long> res4 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            res1.add(traversalEnhanceFor(list));
            res2.add(traversalSimpleFor(list));
            res3.add(traversalStream(list));
            res4.add(traversalParallelStream(list));
        }
        log("enhanceFor cost " + sum(res1) + " ms");
        log("simpleFor cost " + sum(res2) + " ms");
        log("stream cost " + sum(res3) + " ms");
        log("parallelStream cost " + sum(res4) + " ms");
    }

    public Long sum(List<Long> result) {
        return result.stream().reduce(0L, Long::sum);
    }

    @Override
    void doSomething(Person person) {
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
