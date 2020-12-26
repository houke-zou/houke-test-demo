package org.houke.demo.springboot.util;

import org.springframework.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Program :        houke-practice-demo
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-20  17:23
 */
public class BeanUtils {
    private static final Map<String, BeanCopier> BEAN_COPIER_MAP = new ConcurrentHashMap<>();

    /**
     * copyProperties
     * @param source the source bean
     * @param target the target bean
     */
    public static void copyProperties(Object source, Object target) {
        Objects.requireNonNull(source, "source must not be null");
        Objects.requireNonNull(target, "target must not be null");
        BeanCopier beanCopier = getBeanCopier(source.getClass(), target.getClass());
        beanCopier.copy(source, target, null);
    }

    /**
     * @param source the source class
     * @param target the target class
     * @return the bean copier of source class and target class
     */
    private static BeanCopier getBeanCopier(Class<?> source, Class<?> target) {
        String key = generateKey(source, target);
        return BEAN_COPIER_MAP.computeIfAbsent(key, x -> BeanCopier.create(source, target, false));
    }

    /**
     * @param source data source
     * @param target data target
     * @return the key for <code>BEAN_COPIER_MAP</code>
     */
    private static String generateKey(Class<?> source, Class<?> target) {
        return source.getCanonicalName().concat(target.getCanonicalName());
    }
}

