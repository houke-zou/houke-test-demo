package com.houke.demo.basic.domain;

/**
 * @Program :        houke-practice-demo
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-18  16:37
 */
public interface Resultable {
    boolean isSuccess();
    String getMsgCode();
    String getMsgInfo();
    Resultable newErrorInstance(String msgCode, String msgInfo);
}
