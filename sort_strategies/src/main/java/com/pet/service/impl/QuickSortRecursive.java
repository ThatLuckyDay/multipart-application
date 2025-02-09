package com.pet.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("quickSortRecursive")
public class QuickSortRecursive extends QuickSort {

    public int[] sort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }
        return quickSort(array, 0, array.length - 1);
    }

    private int[] quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return array;
        }

        int pivotIndex = partition(array, left, right);

        quickSort(array, left, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, right);
        return array;
    }
}
