package com.pet.service;

import lombok.NoArgsConstructor;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@NoArgsConstructor
public class TimeAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, IllegalArgumentException exception) {
        System.out.println("Illegal exception : " + exception.getClass().getName());
    }
}
