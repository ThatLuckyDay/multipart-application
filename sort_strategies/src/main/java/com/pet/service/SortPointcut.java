package com.pet.service;

import com.pet.service.sortImpl.MergeSort;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SortPointcut extends DynamicMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("Check method for " + method.getName());
        return ((int[]) args[0]).length > 5;
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> clazz == MergeSort.class;
    }

}
