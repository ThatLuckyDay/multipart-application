package com.pet.service.adapter;

import com.pet.service.impl.QuickSort;
import com.pet.service.type.Recursive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mergeSortRecursive")
public class RecursiveMergeSortAdapter implements Recursive {
    private QuickSort quickSort;

    @Autowired
    public RecursiveMergeSortAdapter(QuickSort quickSort) {
        this.quickSort = quickSort;
    }


    @Override
    public int[] sort(int[] array) {
        return quickSort.sortRecursive(array);
    }
}
