package com.pet.service;

import com.pet.model.Sorter;
import com.pet.repository.SorterRepository;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SorterService {

    @Autowired
    private SorterRepository sorterRepository;

    @Autowired
    @Qualifier(value = "mergeSort")
    private InplaceSort mergeSort;

    @Autowired
    @Qualifier(value = "quickSort")
    private InplaceSort quickSort;

    private final TimeAdvice timeAdvice = new TimeAdvice();

    private final ProxyFactory proxyFactory= new ProxyFactory();

    private final Pointcut pointcut = new SortPointcut();

    private final Advisor advisor = new DefaultPointcutAdvisor(pointcut, timeAdvice);

    {
        proxyFactory.addAdvisor(advisor);
    }

    private long sortProceed(int[] array) {
        long time = System.nanoTime();
        Arrays.stream(((InplaceSort) proxyFactory.getProxy()).sort(array))
                .forEach(elem -> System.out.print(elem + " "));
        System.out.println();
        return System.nanoTime() - time;
    }

    public Sorter addSortedByMergeSort(int[] array) {
        proxyFactory.setTarget(mergeSort);

        Sorter sorter = new Sorter(
                mergeSort.getClass().getName(),
                array.length,
                sortProceed(array));

        sorterRepository.getSorters().add(sorter);

        return sorter;
    }

    public Sorter addSortedByQuickSort(int[] array) {
        proxyFactory.setTarget(quickSort);

        Sorter sorter = new Sorter(
                quickSort.getClass().getName(),
                array.length,
                sortProceed(array));

        sorterRepository.getSorters().add(sorter);

        return sorter;
    }

    public List<Sorter> getAllSorters() {
        return sorterRepository.getSorters();
    }
}
