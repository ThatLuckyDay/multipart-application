package com.pet.service;

import lombok.NoArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@NoArgsConstructor
public class TimeAdvice implements MethodBeforeAdvice, AfterReturningAdvice, MethodInterceptor {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        long startTime = System.nanoTime();
        System.out.println("Start sort at: " + startTime);
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        long time = System.nanoTime();
        System.out.println("Stop sort at: " + time);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Start around");
        int[] values = (int[]) invocation.proceed();
        Arrays.stream(values != null ? values : new int[0]).forEach(elem -> System.out.print(elem + " "));
        System.out.println("Stop around");
        return values;
    }
}
