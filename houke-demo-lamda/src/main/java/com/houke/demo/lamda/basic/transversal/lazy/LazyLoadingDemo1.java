package com.houke.demo.lamda.basic.transversal.lazy;

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
@Component
public class LazyLoadingDemo1 extends Transversal {
    private int a = 13;

    public void transversal(Long num) {
        List<Person> list = createOriginalList(num);
        transversalAll(list);
    }

    @Override
    public void doSomething(Person person) {
        super.doSomething(person);
        person.setRandom(a);
    }
}
