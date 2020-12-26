package org.houke.demo.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Program :        springboot-4test
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-23  22:35
 */
public class TraditionalAndStream {
        public static void main(String[] args) {
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            int forNumber = 50;
            for (int i = 0; i < forNumber; i++) {
                list1.add((int) (Math.random() * 100 + 1));
                list2.add((int) (Math.random() * 100 + 1));
            }
            traditionMethod(list1, list2);
            streamMethod(list1, list2);

        }

        private static void traditionMethod(List<Integer> list1, List<Integer> list2) {
            long start = System.currentTimeMillis();
            //50-60
            List<Integer> listOne = new ArrayList<>();
            for (Integer integer : list1) {
                if (integer >= 50 && integer < 60) {
                    listOne.add(integer);
                }
            }
            //前三个
            List<Integer> listOneByTwo = new ArrayList<>();
            for (int i = 0; i < listOne.size(); i++) {
                if (i < 3) {
                    listOneByTwo.add(listOne.get(i));
                }
            }
            //60-70
            List<Integer> listTwo = new ArrayList<>();
            for (Integer integer : list2) {
                if (integer >= 60 && integer <= 70) {
                    listTwo.add(integer);
                }
            }
            //不要前两个
            List<Integer> listTwoByTwo = new ArrayList<>();
            for (int i = 0; i < listTwo.size(); i++) {
                if (i > 1) {
                    listTwoByTwo.add(listTwo.get(i));
                }
            }
            //IntegerEntity封装
            List<IntegerEntity> generate = new ArrayList<>();
            for (int i = 0; i < listOneByTwo.size(); i++) {
                generate.add(new IntegerEntity(listOneByTwo.get(i)));
            }
            for (int i = 0; i < listTwoByTwo.size(); i++) {
                generate.add(new IntegerEntity(listTwoByTwo.get(i)));
            }
            System.out.println(generate);
            long end = System.currentTimeMillis();
            System.out.println(end - start);

        }

        private static void streamMethod(List<Integer> list1, List<Integer> list2) {
            long start = System.currentTimeMillis();
            //50-60
            //前三个
            Stream<Integer> stream1 = list1.stream().filter(number -> number >= 50 && number < 60).limit(3);
            //60-70
            //不要前两个
            Stream<Integer> stream2 = list2.stream().filter(number -> number >= 60 && number < 70).skip(2);
            //IntegerEntity封装
            List<Integer> collect = Stream.concat(stream1, stream2).collect(Collectors.toList());
            System.out.println(collect);
//            Stream<Integer> concat = Stream.concat(stream1, stream2);
//            List<IntegerEntity> generate = new ArrayList<>();
//            concat.forEach((number) -> generate.add(new IntegerEntity(number)));
//            System.out.println(generate);
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
}
