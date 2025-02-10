package com.pet.service;

import com.pet.model.Sorter;
import com.pet.repository.SorterRepository;
import com.pet.service.advice.IsModified;
import com.pet.service.advice.IsModifiedAdvisor;
import com.pet.service.advice.SortPointcut;
import com.pet.service.advice.TimeAdvice;
import com.pet.service.impl.InplaceSort;
import org.springframework.aop.Advisor;
import org.springframework.aop.IntroductionAdvisor;
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

    private final InplaceSort mergeSortRecursive;

    private final InplaceSort quickSortRecursive;

    private final InplaceSort mergeSortIterative;

    private final InplaceSort quickSortIterative;

    @Autowired
    public SorterService(SorterRepository sorterRepository,
                         @Qualifier("mergeSortRecursive") InplaceSort mergeSortRecursive,
                         @Qualifier("quickSortRecursive") InplaceSort quickSortRecursive,
                         @Qualifier("mergeSortIterative") InplaceSort mergeSortIterative,
                         @Qualifier("quickSortIterative") InplaceSort quickSortIterative) {
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

    private long sortProceed(int[] array) {
        long time = System.nanoTime();
        Arrays.stream(((InplaceSort) proxyFactory.getProxy()).sort(array))
                .forEach(elem -> System.out.print(elem + " "));
        System.out.println();
        return System.nanoTime() - time;
    }

    public Sorter addSortedByMergeSortIterative(int[] array) {
        proxyFactory.setTarget(mergeSortIterative);

        Sorter sorter = new Sorter(
                mergeSortIterative.getClass().getName(),
                array.length,
                sortProceed(array));

        sorterRepository.getSorters().add(sorter);

        /* introduction */
        IntroductionAdvisor introductionAdvisor = new IsModifiedAdvisor();
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(sorter);
        pf.addAdvisor(introductionAdvisor);
        pf.setOptimize(true);
        Sorter proxy = (Sorter) pf.getProxy();
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("Is modified? " + proxyInterface.isModified());
        System.out.println("Change capacity!");
        proxy.setCapacity(20);
        System.out.println("Is modified? " + proxyInterface.isModified());

        return sorter;
    }

    public Sorter addSortedByMergeSortRecursive(int[] array) {
        proxyFactory.setTarget(mergeSortRecursive);

        Sorter sorter = new Sorter(
                mergeSortRecursive.getClass().getName(),
                array.length,
                sortProceed(array));

        sorterRepository.getSorters().add(sorter);

        return sorter;
    }

    public Sorter addSortedByQuickSortIterative(int[] array) {
        proxyFactory.setTarget(quickSortIterative);

        Sorter sorter = new Sorter(
                quickSortIterative.getClass().getName(),
                array.length,
                sortProceed(array));

        sorterRepository.getSorters().add(sorter);

        return sorter;
    }

    public Sorter addSortedByQuickSortRecursive(int[] array) {
        proxyFactory.setTarget(quickSortRecursive);

        Sorter sorter = new Sorter(
                quickSortRecursive.getClass().getName(),
                array.length,
                sortProceed(array));

        sorterRepository.getSorters().add(sorter);

        return sorter;
    }

    public List<Sorter> getAllSorters() {
        return sorterRepository.getSorters();
    }
}
