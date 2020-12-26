package com.houke.demo.basic.domain;

/**
 * @Program :        houke-practice-demo
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-18  16:49
 */
public enum StateEnum {
    SUCCESS("业务执行成功"),
    BIZ_ERROR("业务执行错误"),
    SYSTEM_ERROR("服务器系统错误"),
    PARAM_ERROR("参数错误"),
    DATA_ERROR("数据解析错误"),

    DATA_NOTEXIST("数据不存在"),
    ;

    private String message;

    StateEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.name();
    }

    public String toString() {
        return this.name() + "|" + this.message;
    }
}
