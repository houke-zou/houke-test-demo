package com.houke.demo.lamda.basic.transversal;

import com.houke.demo.basic.LogUtil;
import com.houke.demo.lamda.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @program :        houke-test-demo
 * @Author :         houke_zou
 * @date :           2020-12-26  15:19
 * @description :
 */
public abstract class Transversal {

    public List<Person> createOriginalList(Long num) {
        long start = System.currentTimeMillis();
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer result = (int) (Math.random() * 100 + 1);
            list.add(new Person(1L, "a", "b", "c", "d", "e", "f", result));
        }
        long end = System.currentTimeMillis();
        LogUtil.log("the cost of create srcList is " + (end - start));
        return list;
    }

    protected void transversalAll(List<Person> list) {
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
        LogUtil.log("enhanceFor cost " + sum(res1) + " ms");
        LogUtil.log("simpleFor cost " + sum(res2) + " ms");
        LogUtil.log("stream cost " + sum(res3) + " ms");
        LogUtil.log("parallelStream cost " + sum(res4) + " ms");
    }

    private Long sum(List<Long> result) {
        return result.stream().reduce(0L, Long::sum);
    }

    public Long traversalEnhanceFor(List<Person> list) {
        long start = System.currentTimeMillis();
        for (Person a : list) {
            doSomething(a);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public Long traversalSimpleFor(List<Person> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            doSomething(list.get(i));
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public Long traversalStream(List<Person> list) {
        long start = System.currentTimeMillis();
        list.stream().forEach(this::doSomething);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public Long traversalParallelStream(List<Person> list) {
        long start = System.currentTimeMillis();
        list.parallelStream().forEach(this::doSomething);
        long end = System.currentTimeMillis();
        return end - start;
    }

    protected void doSomething(Person person) {
        String temp;
        temp = person.getA();
        person.setA(person.getB());
        person.setB(person.getC());
        person.setC(person.getD());
        person.setD(person.getE());
        person.setE(person.getF());
        person.setF(temp);
    }
}
