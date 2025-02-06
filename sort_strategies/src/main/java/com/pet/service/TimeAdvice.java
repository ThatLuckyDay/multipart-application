package com.pet.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@NoArgsConstructor
public class TimeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        long startTime = System.nanoTime();
        System.out.println("Start sort at: " + startTime);
    }
}
