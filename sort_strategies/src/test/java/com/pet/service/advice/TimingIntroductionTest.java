package com.pet.service.advice;

import com.pet.service.impl.InplaceSort;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TimingIntroductionTest {

    @Test
    void sortMethod_RecordsDuration() {
        InplaceSort dummySort = arr -> arr;

        ProxyFactory factory = new ProxyFactory(dummySort);
        factory.addAdvisor(new TimingAdvisor());
        InplaceSort proxy = (InplaceSort) factory.getProxy();

        proxy.sort(new int[]{5, 3, 1});

        TimedExecution timedProxy = (TimedExecution) proxy;
        assertTrue(timedProxy.getDuration() > 0, "Duration should be recorded");
    }
}