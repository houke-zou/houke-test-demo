package com.houke.demo.lamda.basic.transversal.base;

import com.houke.demo.lamda.basic.transversal.Transversal;
import com.houke.demo.lamda.domain.Person;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program :        houke-test-demo
 * @Author :         houke_zou
 * @date :           2020-12-26  15:22
 * @description :
 */
//@Lazy
@Component
public class FourTypeTransversalDemo1 extends Transversal {
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

    public void transversal(Long num) {
        List<Person> list = createOriginalList(num);
        transversalAll(list);
    }

    @Override
    protected void doSomething(Person person) {
        super.doSomething(person);
        person.setRandom(a);
    }
}
