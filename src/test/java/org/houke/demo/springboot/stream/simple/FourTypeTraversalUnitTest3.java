package org.houke.demo.springboot.stream.simple;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program :        springboot-4test
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-19  21:27
 */
public class FourTypeTraversalUnitTest3 extends TraversalUnitTest {

    /**
     * 注: 五次总和, 单位(ms)
     *
     * random = a;
     * 循环次数         100000      1000000     10000000    50000000
     * 增强for循环      10          43          447         2032
     * 普通for循环      9           37          360         1786
     * 串行stream      5           35          454         2045
     * 并行stream      15          34          204         1188
     *
     * random = b
     * 循环次数         100000      1000000     10000000    50000000
     * 增强for循环      11          45          447         2002
     * 普通for循环      9           41          360         1737
     * 串行stream      6           41          454         2089
     * 并行stream      16          39          204         1171
     *
     * random = c
     * 循环次数         100000      1000000     10000000    50000000
     * 增强for循环      10          39          354         1732
     * 普通for循环      9           35          317         1587
     * 串行stream      6           31          405         1861
     * 并行stream      17          35          207         1162
     *
     * random = d
     * 循环次数         100000      1000000     10000000    50000000
     * 增强for循环      19          93          880         4223
     * 普通for循环      13          84          857         4139
     * 串行stream      9           81          877         3838
     * 并行stream      16          46          219         1161
     */

    private int a = 13;
    private static int b = 13;
    private Integer c = 13;
    private String d = "13";

    @Test
    public void transversal() {
        List<Person> list = createOriginalList(100000L);
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
//        demoTestBean.setRandom(a);
//        demoTestBean.setRandom(b);
//        demoTestBean.setRandom(c);
        person.setRandom(Integer.valueOf(d));
    }
}
