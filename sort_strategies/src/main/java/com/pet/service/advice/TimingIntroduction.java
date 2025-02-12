package com.pet.service.advice;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TimingIntroduction extends DelegatingIntroductionInterceptor implements TimedExecution {
    private long duration = Long.MAX_VALUE;

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        if (mi.getMethod().getName().equals("sort")) {
            this.duration = System.nanoTime();
            int[] retVal = (int[]) super.invoke(mi);

            this.duration = System.nanoTime() - this.duration;

            assert retVal != null;
            IntStream.of(retVal).forEach(elem -> System.out.print(elem + " "));
            System.out.println("duration " + mi.getMethod().getDeclaringClass().getSimpleName() + " " + duration);

            return retVal;
        }
        return super.invoke(mi);
    }

    @Override
    public long getDuration() {
        return this.duration;
    }
}
