package com.mfun.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class LogUtil {

    @Pointcut("execution(public int com.mfun.proxy.Calculator.div(int, int))")
    @Order(1)
    public void addPointcut() {}

    @Before("addPointcut()")
    public static void start(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("执行 Before，参数：" + Arrays.toString(args));
    }

    @AfterReturning(value = "addPointcut()", returning = "result")
    public static int returning(JoinPoint joinPoint, int result) {
        System.out.println("执行 AfterReturning，结果为：" + result);
        return 99;
    }

    @AfterThrowing(value = "addPointcut()", throwing = "e")
    public static void throwing(Exception e) {
        System.out.println("执行 AfterThrowing，异常为：" + e.getMessage());
    }

    @After("addPointcut()")
    public static void ff() {
        System.out.println("执行 After");
    }

//    @Around("addPointcut()")
//    public static Object around(ProceedingJoinPoint pjp) throws Throwable {
//        try {
//            // 前置操作
//            ...
//            Object[] args = pjp.getArgs();
//            // 调用目标方法并返回结果
//            Object result = pjp.proceed(args);
//            // 返回时操作
//            ...
//            return result;
//        } catch (Throwable e) {
//            // 抛出异常时操作
//            ...
//            throw e;
//        } finally {
//            // 后置操作
//            ...
//        }
//    }

}
