package org.houke.demo.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.houke.demo.springboot.aop.basic.Simple;

/**
 * @Program :        springboot-4test
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-25  17:14
 */
@Data
@AllArgsConstructor
public class FirstDemoBean {

    @Simple
    private String key;
    private String backup;
}
