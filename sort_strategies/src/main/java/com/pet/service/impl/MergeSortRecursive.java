package com.pet.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mergeSortRecursive")
public class MergeSortRecursive extends MergeSort {

    public int[] sort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }

        int[] result = new int[array.length];
        System.arraycopy(array, 0, result, 0, array.length);

        mergeSort(result, 0, result.length - 1);
        return result;
    }


    private void mergeSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);

        merge(array, left, mid, right);
    }


}
