package org.houke.demo.springboot.service;

import java.util.Objects;

/**
 * @Program :        springboot-4test
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-23  22:35
 */
public class IntegerEntity {
    @Override
    public String toString() {
        return "IntegerEntity{" +
            "integer=" + integer +
            '}';
    }

    private Integer integer;

    public IntegerEntity(){}

    public IntegerEntity(Integer integer) {
        this.integer = integer;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerEntity that = (IntegerEntity) o;
        return Objects.equals(integer, that.integer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(integer);
    }
}