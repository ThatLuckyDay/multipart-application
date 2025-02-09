package com.pet.service.adapter;

import com.pet.service.impl.QuickSort;
import com.pet.service.type.Iterative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mergeSortIterative")
public class IterativeMergeSortAdapter implements Iterative {
    private final QuickSort quickSort;

    @Autowired
    public IterativeMergeSortAdapter(QuickSort quickSort) {
        this.quickSort = quickSort;
    }

    @Override
    public int[] sort(int[] array) {
        return quickSort.sortIterative(array);
    }
}
