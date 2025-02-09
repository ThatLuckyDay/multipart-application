package com.pet.service.impl;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;

@Component(value = "quickSortIterative")
public class QuickSortIterative extends QuickSort {

    public int[] sort(int[] array) {
        if (array == null || array.length <= 1) {
            throw new IllegalArgumentException();
        }

        int[] result = new int[array.length];
        System.arraycopy(array, 0, result, 0, array.length);

        Deque<Integer> stack = new ArrayDeque<>();
        int low = 0;
        int high = result.length - 1;

        stack.push(low);
        stack.push(high);

        while (!stack.isEmpty()) {
            high = stack.pop();
            low = stack.pop();

            int pivotIndex = partition(result, low, high);

            if (pivotIndex + 1 < high) {
                stack.push(pivotIndex + 1);
                stack.push(high);
            }

            if (low < pivotIndex - 1) {
                stack.push(low);
                stack.push(pivotIndex - 1);
            }
        }
        return result;
    }

}

