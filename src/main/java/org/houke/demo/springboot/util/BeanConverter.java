package org.houke.demo.springboot.util;

import net.jodah.typetools.TypeResolver;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Program :        houke-practice-demo
 * @Author :         houke_zou
 * @date :           2020-12-20  20:32
 * @Description :
 */
public class BeanConverter {

    private final Converter converter;
    private final Map<String, BeanCopier> beanCopierMap;

    /**
     * @param converters converters
     */
    BeanConverter(List<TypeConverter<?, ?>> converters) {
        this.converter = new ConverterAdapter(converters);
        this.beanCopierMap = new ConcurrentHashMap<>();
    }

    /**
     * the main method for copy properties
     * @param source the source bean
     * @param target the target bean
     */
    public void copyProperties(Object source, Object target) {
        Objects.requireNonNull(source, "source must not be null");
        Objects.requireNonNull(target, "target must not be null");

        BeanCopier beanCopier = getBeanCopier(source.getClass(), target.getClass());
        beanCopier.copy(source, target, converter);
    }

    /**
     * @param source the source class
     * @param target the target class
     * @return the bean copier of source class and target class
     */
    private BeanCopier getBeanCopier(Class<?> source, Class<?> target) {
        String key = generateKey(source, target);
        return beanCopierMap.computeIfAbsent(key, x -> BeanCopier.create(source, target, true));
    }

    /**
     * @param source the source class
     * @param target the target class
     * @return the key of <code>beanCopierMap</code> by source class and target class
     */
    private String generateKey(Class<?> source, Class<?> target) {
        return source.getCanonicalName().concat(target.getCanonicalName());
    }

    /**
     * The adapter class that turns {@link TypeConverter} into {@link Converter}.
     */
    static class ConverterAdapter implements Converter {
        private final Map<Class<?>, List<ResolvedTypeConverter>> converterMap;
        ConverterAdapter(List<TypeConverter<?, ?>> converterMap) {
            this.converterMap = converterMap.stream()
                    .map(this::resolveTypeConverter)
                    .collect(Collectors.groupingBy(ResolvedTypeConverter::getSourceType));
        }

        // 实测 : 使用 lamda 表达式               耗时 : 332  276  317  295  239  266
        // 实测 : 使用 lamda limit限制            耗时 : 286  287  303  327  271  337
        // 实测 : 使用 parallelStream()          耗时 : 529  508  473  485  500  464
        // 实测 : 使用 for 循环                   耗时 : 180  194  227  239  238  203
        // 实测 : 使用 for 循环 及时返回           耗时 : 178  187  183  224  230  208

        @SuppressWarnings("unchecked")
        @Override
        public Object convert(Object value, Class targetType, Object context) {
            if (value == null || ClassUtils.isAssignable(value.getClass(), targetType, true)) {
                return value;
            }
            Object result = null;
//            return converterMap.entrySet().parallelStream()
//                .filter(src -> ClassUtils.isAssignable(value.getClass(), src.getKey(), true))
//                .map(Map.Entry::getValue)
//                .map(src -> src.stream()
//                    .filter(srcItem -> ClassUtils.isAssignable(srcItem.getTargetType(), targetType, true))
//                    .map(srcItem -> srcItem.convert(value))
//                    .limit(1)
//                    .findFirst().orElse(null))
//                .limit(1)
//                .findFirst().orElse(null);

            // Find the mapping strategy from map
            for (Map.Entry<Class<?>, List<ResolvedTypeConverter>> entry : converterMap.entrySet()) {
                List<ResolvedTypeConverter> converters = entry.getValue();
                // Determine if the source type of converter is assignable from the type of value
                if (ClassUtils.isAssignable(value.getClass(), entry.getKey(), true)) {
                    for (ResolvedTypeConverter converter : converters) {
                        // Determine if the target type to convert is assignable from the target type of converter
                        if (ClassUtils.isAssignable(converter.getTargetType(), targetType, true)) {
                            return converter.convert(value);
                        }
                    }
                }
            }
            return null;
        }

        @SuppressWarnings("unchecked")
        private <S, T> ResolvedTypeConverter<S, T> resolveTypeConverter(TypeConverter<S, T> converter) {
            Class<?>[] classes = TypeResolver.resolveRawArguments(TypeConverter.class, converter.getClass());
            System.out.println(TypeConverter.class + "" + converter.getClass());
            return new ResolvedTypeConverter<>(converter, (Class<S>) classes[0], (Class<T>) classes[1]);
        }

        /**
         * @param <S> the source type
         * @param <T> the target type
         */
        static class ResolvedTypeConverter<S, T> implements TypeConverter<S, T> {

            private final TypeConverter<S, T> delegatingConverter;
            private final Class<S> sourceType;
            private final Class<T> targetType;

            ResolvedTypeConverter(TypeConverter<S, T> delegatingConverter, Class<S> sourceType,
                                  Class<T> targetType) {
                this.delegatingConverter = delegatingConverter;
                this.sourceType = sourceType;
                this.targetType = targetType;
            }

            public Class<S> getSourceType() {
                return sourceType;
            }
            public Class<T> getTargetType() {
                return targetType;
            }
            public T convert(S source) {
                return delegatingConverter.convert(source);
            }
        }
    }
}
