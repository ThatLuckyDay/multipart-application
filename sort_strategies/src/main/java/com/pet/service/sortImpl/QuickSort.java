package com.pet.service.sortImpl;

import com.pet.service.InplaceSort;
import org.springframework.stereotype.Component;

@Component(value = "quickSort")
public class QuickSort implements InplaceSort {
    @Override
    public int[] sort(int[] array) {
        return new int[0];
    }
}
