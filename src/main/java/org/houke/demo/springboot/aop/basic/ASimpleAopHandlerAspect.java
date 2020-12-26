package org.houke.demo.springboot.aop.basic;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.houke.demo.springboot.domain.Resultable;
import org.houke.demo.springboot.domain.StateEnum;
import org.houke.demo.springboot.exception.GlobalException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Program :        houke-practice-demo
 * @Description :
 * @Author :         houke_zou
 * @date :           2020-12-18  13:10
 */
@Order(50)
@Component
@Aspect
public class ASimpleAopHandlerAspect {
    public static final String SCENE = "BASE_HANDLER_AOP";

    @Pointcut("execution(@org.houke.demo.springboot.aop.basic.SimpleAopHandler * *.*(..))")
    void annotatedMethod() {}

    @Pointcut("execution(public * (@org.houke.demo.springboot.aop.basic.SimpleAopHandler *).*(..))")
    void methodOfAnnotatedClass() {}

    @Around("annotatedMethod() && @annotation(simpleAopHandler)")
    public Object handle1(ProceedingJoinPoint joinPoint, SimpleAopHandler simpleAopHandler) throws Throwable {
        System.out.println("进入了AOP");
        long start = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        Object result = null;
        String className = joinPoint.getSignature().getDeclaringType().getName();
        String methodName = joinPoint.getSignature().getName();
        String classPath = joinPoint.getSignature().getDeclaringType().getName();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> resultType = method.getReturnType();
        System.out.println("the args is : " + args);
        System.out.println("the className is : " + className);
        System.out.println("the methodName is : " + methodName);
        System.out.println("the classPath is : " + classPath);
        System.out.println("the signature is : " + signature);
        System.out.println("the resultType is : " + resultType);

        if (simpleAopHandler == null) {
            return joinPoint.proceed();
        }

        Throwable e = null;

        try {
            result = joinPoint.proceed();
            System.out.println("退出AOP1");
        } catch (Throwable throwable) {
            e = throwable;
        }

        Object resultable = handleException(e, simpleAopHandler, resultType);
        return null == resultable ? result : resultable;
    }

    private Object handleException(Throwable throwable, SimpleAopHandler handler, Class<?> resultType) throws Throwable{
        if (throwable == null
            || resultType == null
            || Resultable.class.isAssignableFrom(resultType)) {
            return null;
        }
        try {
            throw throwable;
        } catch (GlobalException e) {
            System.out.println("捕获全局异常");
            return ((Resultable) resultType.newInstance()).newErrorInstance(e.getMsgCode(), e.getMessage());
        } catch (Throwable e) {
            String code = StateEnum.BIZ_ERROR.getCode();
            String message = StateEnum.BIZ_ERROR.getMessage();
            return ((Resultable) resultType.newInstance()).newErrorInstance(code, message);
        }
    }
}