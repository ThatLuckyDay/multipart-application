package com.pet.service.advice;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;

public class SortPointcut extends AspectJExpressionPointcut {

    public SortPointcut() {
        super();
        this.setExpression("execution(*recursive*(..))");
    }

}
