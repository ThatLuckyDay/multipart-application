package com.pet.service;

import lombok.NoArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@NoArgsConstructor
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Before around actions. Method: " + invocation.getMethod().getName() +
                " " + invocation.getMethod().getDeclaringClass().getName());
        Object result = invocation.proceed();
        System.out.println("Sort done!");
        System.out.println("After around actions. Method: " + invocation.getMethod().getName() +
                " " + invocation.getMethod().getDeclaringClass().getName());
        return result;
    }
}
