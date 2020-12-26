package com.houke.demo.lamda.basic.transversal.base;

import com.houke.demo.lamda.basic.transversal.Transversal;
import com.houke.demo.lamda.domain.Person;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program :        houke-test-demo
 * @Author :         houke_zou
 * @date :           2020-12-26  15:22
 * @description :
 */
@Lazy
@Component
public class FourTypeTransversalDemo3 extends Transversal {
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

    public void transversal(Long num) {
        List<Person> list = createOriginalList(num);
        transversalAll(list);
    }

    @Override
    protected void doSomething(Person person) {
        super.doSomething(person);
//        person.setRandom(a);
//        person.setRandom(b);
        person.setRandom(c);
//        person.setRandom(Integer.valueOf(d));
    }
}
