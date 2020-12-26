package org.houke.demo.springboot.aop.basic;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * @Program :        houke-practice-demo
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-18  22:28
 */
@Order(50)
@Component
@Aspect
public class SimpleAopHandler2Aspect {
    public static final String SCENE = "BASE_HANDLER_AOP";

    @Pointcut("execution(@org.houke.demo.springboot.aop.basic.SimpleAopHandler2 * *.*(..))")
    void annotatedMethod() {
    }

    @Pointcut("execution(public * (@org.houke.demo.springboot.aop.basic.SimpleAopHandler2 *).*(..))")
    void methodOfAnnotatedClass() {
    }

    @Around("annotatedMethod() && @annotation(simpleAopHandler2)")
    public Object handle1(ProceedingJoinPoint joinPoint, SimpleAopHandler2 simpleAopHandler2) throws Throwable {

        Class<? extends ProceedingJoinPoint> aClass = joinPoint.getClass();
        System.out.println(aClass);

//        Class.forName(joinPoint.getSourceLocation());
//
//        Signature signature = joinPoint.getSignature();
//        MethodSignature signature1 = (MethodSignature) signature;
//        String[] parameterNames = signature1.getParameterNames();
//
//
//        Method method = ((MethodSignature) signature).getMethod();
//        String name = method.getName();
//        Parameter[] parameters = method.getParameters();
//        System.out.println("the method is " + method);
//        AnnotatedType[] annotatedParameterTypes = method.getAnnotatedParameterTypes();
//
//        for (AnnotatedType type : annotatedParameterTypes) {
//            Annotation[] annotations = type.getAnnotations();
//            System.out.println("annotations are " + Arrays.toString(annotations));
//            boolean annotationPresent = type.isAnnotationPresent(Simple.class);
//            System.out.println(annotationPresent);
//        }

//        Object[] args = joinPoint.getArgs();
//        String kind = joinPoint.getKind();
//        SourceLocation sourceLocation = joinPoint.getSourceLocation();
//        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
//        Object target = joinPoint.getTarget();
//
//
//        System.out.println("signature is : " + signature);
//        System.out.println("args is " + Arrays.toString(args));
//        System.out.println("kind is " + kind);
//        System.out.println("sourceLocation is " + sourceLocation);
//        System.out.println("staticPart is " + staticPart);
//        System.out.println("target is " + target);


//        Method method1 = new Method(joinPoint);
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {

        }
        System.out.println(Arrays.deepToString(parameterAnnotations));
        System.out.println("method Method is "  + JSON.toJSONString(method));



        Object proceed = joinPoint.proceed();
        return proceed;
    }
}