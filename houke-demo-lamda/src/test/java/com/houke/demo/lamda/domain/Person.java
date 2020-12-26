package com.houke.demo.lamda.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program :        houke-test-demo
 * @Author :         houke_zou
 * @date :           2020-12-26  14:46
 * @description :
 */
@Data
@AllArgsConstructor
public class Person {
    private Long id;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private Integer random;
}
