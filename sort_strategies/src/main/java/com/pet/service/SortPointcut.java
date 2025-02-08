package com.pet.service;

import com.pet.service.sortImpl.QuickSort;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SortPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "sort".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> clazz == QuickSort.class;
    }

}
