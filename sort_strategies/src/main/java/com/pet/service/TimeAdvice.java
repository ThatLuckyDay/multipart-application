package com.pet.service;

import lombok.NoArgsConstructor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@NoArgsConstructor
public class TimeAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        long startTime = System.nanoTime();
        System.out.println("Start sort at: " + startTime);
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        long time = System.nanoTime();
        System.out.println("Stop sort at: " + time);

        if ("sort".equals(method.getName())) {
            throw new RuntimeException("There should be a check here");
        }
    }
}
