package com.pet.service;

import com.pet.model.Sorter;
import com.pet.repository.SorterRepository;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    @Autowired
    private TimeAdvice timeAdvice;

    private final ProxyFactory proxyFactory = new ProxyFactory();

    public Sorter addSortedByMergeSort(int[] array) {
        proxyFactory.addAdvice(timeAdvice);
        proxyFactory.setTarget(mergeSort);

        long start = System.nanoTime();
        try {
            ((InplaceSort) proxyFactory.getProxy()).sort(array);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        long end = System.nanoTime();

        Sorter sorter = new Sorter(
                mergeSort.getClass().getName(),
                array.length,
                start - end);

        sorterRepository.getSorters().add(sorter);

        return sorter;
    }

    public Sorter addSortedByQuickSort(int[] array) {
        proxyFactory.addAdvice(timeAdvice);
        proxyFactory.setTarget(quickSort);

        long start = System.nanoTime();
        int[] result = ((InplaceSort) proxyFactory.getProxy()).sort(array);
        long end = System.nanoTime();

        Sorter sorter = new Sorter(
                quickSort.getClass().getName(),
                array.length,
                start - end);

        sorterRepository.getSorters().add(sorter);

        return sorter;
    }

    public List<Sorter> getAllSorters() {
        return sorterRepository.getSorters();
    }
}
