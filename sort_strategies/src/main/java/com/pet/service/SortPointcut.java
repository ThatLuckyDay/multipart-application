package com.pet.service;

import com.pet.service.sortImpl.MergeSort;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class SortPointcut extends NameMatchMethodPointcut {

    public SortPointcut() {
        super();
        this.addMethodName("sort");
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> clazz == MergeSort.class;
    }

}
