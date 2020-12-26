package org.houke.demo.springboot.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A builder class that builds a {@link BeanConverter}.
 *
 * @author Robin Wang
 */
public class BeanConverterBuilder {

    private List<TypeConverter<?, ?>> converters = new ArrayList<>();

    /**
     * @param converter the converter required to add into this builder
     */
    public BeanConverterBuilder registerConverter(TypeConverter<?, ?> converter) {
        converters.add(converter);
        return this;
    }

    // 建造者模式核心方法

    public static BeanConverterBuilder builder() {
        return new BeanConverterBuilder();
    }
    public BeanConverter build() {
        return new BeanConverter(converters);
    }
}
