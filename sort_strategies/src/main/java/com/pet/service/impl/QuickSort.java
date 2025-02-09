package com.pet.service.impl;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;

@Component(value = "quickSort")
public class QuickSort extends Strategy {
    private final IterativeStrategy iterativeStrategy = new IterativeStrategy();
    private final RecursiveStrategy recursiveStrategy = new RecursiveStrategy();

    public int[] sortIterative(int[] array) {
        return iterativeStrategy.sort(array);
    }

    public int[] sortRecursive(int[] array) {
        return recursiveStrategy.sort(array);
    }


    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, right);

        return i + 1;
    }

    private static final class IterativeStrategy {

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

    private static final class RecursiveStrategy {

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


}

