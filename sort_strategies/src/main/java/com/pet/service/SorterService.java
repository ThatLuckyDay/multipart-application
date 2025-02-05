package com.pet.service;

import com.pet.model.Sorter;
import com.pet.repository.SorterRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SorterService {

    private SorterRepository sorterRepository;

    private InplaceSort mergeSort;

    private long measureFunctionExecutionTime(InplaceSort sorter, int[] array) {
        long startTime = System.nanoTime();
        sorter.sort(array);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public void addSortedByMergeSort(int[] array) {
        sorterRepository
                .getSorters()
                .add(new Sorter(
                        mergeSort.getClass().getName(),
                        array.length,
                        measureFunctionExecutionTime(mergeSort, array)
                ));
    }

    public List<Sorter> getAllSorters() {
        return sorterRepository.getSorters();
    }
}
