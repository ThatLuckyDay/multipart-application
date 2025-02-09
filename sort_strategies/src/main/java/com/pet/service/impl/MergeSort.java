package com.pet.service.impl;


import org.springframework.stereotype.Component;

@Component(value = "mergeSort")
public class MergeSort extends Strategy {
    private final IterativeStrategy iterativeStrategy = new IterativeStrategy();
    private final RecursiveStrategy recursiveStrategy = new RecursiveStrategy();

    public int[] sortIterative(int[] array) {
        return iterativeStrategy.sort(array);
    }

    public int[] sortRecursive(int[] array) {
        return recursiveStrategy.sort(array);
    }


    private static final class IterativeStrategy {
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

        private void merge(int[] array, int left, int mid, int right) {
            int n1 = mid - left + 1;
            int n2 = right - mid;

            int[] leftArray = new int[n1];
            int[] rightArray = new int[n2];

            System.arraycopy(array, left, leftArray, 0, n1);
            System.arraycopy(array, mid + 1, rightArray, 0, n2);

            int i = 0, j = 0, k = left;
            while (i < n1 && j < n2) {
                if (leftArray[i] <= rightArray[j]) {
                    array[k++] = leftArray[i++];
                } else {
                    array[k++] = rightArray[j++];
                }
            }

            while (i < n1) {
                array[k++] = leftArray[i++];
            }
            while (j < n2) {
                array[k++] = rightArray[j++];
            }
        }
    }

    private static final class RecursiveStrategy {

        public int[] sort(int[] array) {
            if (array == null || array.length < 2) {
                return array;
            }
            return mergeSort(array, 0, array.length - 1);
        }


        private int[] mergeSort(int[] array, int left, int right) {
            if (left >= right) {
                return array;
            }

            int mid = left + (right - left) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            return merge(array, left, mid, right);
        }

        private int[] merge(int[] array, int left, int mid, int right) {
            int sizeLeft = mid - left + 1;
            int sizeRight = right - mid;

            int[] leftArray = new int[sizeLeft];
            int[] rightArray = new int[sizeRight];

            System.arraycopy(array, left, leftArray, 0, sizeLeft);
            System.arraycopy(array, mid + 1, rightArray, 0, sizeRight);

            int i = 0, j = 0, k = left;

            while (i < sizeLeft && j < sizeRight) {
                if (leftArray[i] <= rightArray[j]) {
                    array[k++] = leftArray[i++];
                } else {
                    array[k++] = rightArray[j++];
                }
            }

            while (i < sizeLeft) {
                array[k++] = leftArray[i++];
            }

            while (j < sizeRight) {
                array[k++] = rightArray[j++];
            }
            return array;
        }
    }
}
