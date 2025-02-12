package com.pet.service.advice;

import com.pet.service.impl.InplaceSort;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.stereotype.Component;

@Component
public class TimingAdvisor extends DefaultIntroductionAdvisor {

    public TimingAdvisor() {
        super(new TimingIntroduction(), TimedExecution.class);
    }

    @Override
    public boolean matches(Class<?> clazz) {
        return InplaceSort.class.isAssignableFrom(clazz);
    }
}
