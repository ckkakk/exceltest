package com.cc.cv;


import com.cc.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author clk
 * @date 2022/6/2
 */
@Aspect
@Component
public class asppect {
    @Before("execution(* com.cc.cv.*.*(..))")
    public void df(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log l = method.getAnnotation(Log.class);
        System.out.println(l);
    }

    @After("execution(* com.cc.cv.*.*(..))")
    public void ty(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log l = method.getAnnotation(Log.class);
        System.out.println(l+" 完成 " +l);
    }

    @Around("execution(* com.cc.cv.*.*(..))")
    public void ewtq(JoinPoint joinPoint){


    }
}
