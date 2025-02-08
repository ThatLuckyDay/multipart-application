package com.pet.service.sortImpl;

import com.pet.service.InplaceSort;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;

@Component(value = "quickSort")
public class QuickSort implements InplaceSort {

    @Override
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

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

