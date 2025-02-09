package com.pet.service;

import com.pet.model.Sorter;
import com.pet.repository.SorterRepository;
import com.pet.service.advice.SortPointcut;
import com.pet.service.advice.TimeAdvice;
import com.pet.service.type.Iterative;
import com.pet.service.type.Recursive;
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

    private final SorterRepository sorterRepository;

    private final Recursive mergeSortRecursive;

    private final Recursive quickSortRecursive;

    private final Iterative mergeSortIterative;

    private final Iterative quickSortIterative;

    @Autowired
    public SorterService(SorterRepository sorterRepository,
                         @Qualifier("mergeSortRecursive") Recursive mergeSortRecursive,
                         @Qualifier("quickSortRecursive") Recursive quickSortRecursive,
                         @Qualifier("mergeSortIterative") Iterative mergeSortIterative,
                         @Qualifier("quickSortIterative") Iterative quickSortIterative) {
        this.sorterRepository = sorterRepository;
        this.quickSortRecursive = quickSortRecursive;
        this.mergeSortRecursive = mergeSortRecursive;
        this.mergeSortIterative = mergeSortIterative;
        this.quickSortIterative = quickSortIterative;
    }

    private final TimeAdvice timeAdvice = new TimeAdvice();

    private final ProxyFactory proxyFactory = new ProxyFactory();

    private final Pointcut pointcut = new SortPointcut();

    private final Advisor advisor = new DefaultPointcutAdvisor(pointcut, timeAdvice);

    {
        proxyFactory.addAdvisor(advisor);
    }

    private long sortRecursive(int[] array) {
        long time = System.nanoTime();
        Arrays.stream(((Recursive) proxyFactory.getProxy()).sort(array))
                .forEach(elem -> System.out.print(elem + " "));
        System.out.println();
        return System.nanoTime() - time;
    }

    private long sortIterative(int[] array) {
        long time = System.nanoTime();
        Arrays.stream(((Recursive) proxyFactory.getProxy()).sort(array))
                .forEach(elem -> System.out.print(elem + " "));
        System.out.println();
        return System.nanoTime() - time;
    }

    public Sorter addSortedByMergeSort(int[] array) {
//        proxyFactory.setTarget(mergeSort);
//
//        Sorter sorter = new Sorter(
//                mergeSort.getClass().getName(),
//                array.length,
//                sortProceed(array));
//
//        sorterRepository.getSorters().add(sorter);
//
//        return sorter;
        return null;
    }

    public Sorter addSortedByQuickSort(int[] array) {
//        proxyFactory.setTarget(quickSort);
//
//        Sorter sorter = new Sorter(
//                quickSort.getClass().getName(),
//                array.length,
//                sortProceed(array));
//
//        sorterRepository.getSorters().add(sorter);
//
//        return sorter;
        return null;
    }

    public List<Sorter> getAllSorters() {
        return sorterRepository.getSorters();
    }
}
