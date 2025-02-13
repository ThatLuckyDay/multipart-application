package com.pet.service;

import com.pet.model.Sorter;
import com.pet.repository.SorterRepository;
import com.pet.service.advice.TimedExecution;
import com.pet.service.impl.InplaceSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    public Sorter addSortedByMergeSortIterative(int[] array) {
        TimedExecution proxyInterface = (TimedExecution) mergeSortIterative;

        mergeSortIterative.sort(array);

        Sorter sorter = new Sorter(
                mergeSortIterative.getClass().getName(),
                array.length,
                proxyInterface.getDuration());

        sorterRepository.getSorters().add(sorter);

        return sorter;
    }

    public Sorter addSortedByMergeSortRecursive(int[] array) {
        TimedExecution proxyInterface = (TimedExecution) mergeSortRecursive;

        mergeSortRecursive.sort(array);

        Sorter sorter = new Sorter(
                mergeSortRecursive.getClass().getName(),
                array.length,
                proxyInterface.getDuration());

        sorterRepository.getSorters().add(sorter);

        return sorter;
    }

    public Sorter addSortedByQuickSortIterative(int[] array) {
        TimedExecution proxyInterface = (TimedExecution) quickSortIterative;

        quickSortIterative.sort(array);

        Sorter sorter = new Sorter(
                quickSortIterative.getClass().getName(),
                array.length,
                proxyInterface.getDuration());

        sorterRepository.getSorters().add(sorter);

        return sorter;
    }

    public Sorter addSortedByQuickSortRecursive(int[] array) {
        TimedExecution proxyInterface = (TimedExecution) quickSortRecursive;

        quickSortRecursive.sort(array);

        Sorter sorter = new Sorter(
                quickSortRecursive.getClass().getName(),
                array.length,
                proxyInterface.getDuration());

        sorterRepository.getSorters().add(sorter);

        return sorter;
    }

    public List<Sorter> getAllSorters() {
        return sorterRepository.getSorters();
    }
}
