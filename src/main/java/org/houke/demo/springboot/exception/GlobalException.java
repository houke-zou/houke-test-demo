package org.houke.demo.springboot.exception;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @Program :        houke-practice-demo
 * @Description :    本项目的全局异常, 尽可能抛出此异常的子类或此异常
 * @Author :         houke_zou
 * @date :           2020-12-18  11:20
 */
@Getter
public class GlobalException extends RuntimeException{
    private String msgCode;
    private String msgInfo;

    public GlobalException(String code, String info) {
        super(StringUtils.isNotBlank(info) ? StringUtils.join(new String[] {code, info}, ",") : code);
    }
}
