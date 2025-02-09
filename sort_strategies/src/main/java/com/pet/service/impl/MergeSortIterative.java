package com.pet.service.impl;


import org.springframework.stereotype.Component;

@Component(value = "mergeSortIterative")
public class MergeSortIterative extends MergeSort {

    public int[] sort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }

        int n = array.length;

        int[] result = new int[n];
        System.arraycopy(array, 0, result, 0, n);

        for (int size = 1; size < n; size *= 2) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);

                merge(result, left, mid, right);
            }
        }
        return result;
    }

}
