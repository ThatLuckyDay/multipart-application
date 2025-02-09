package com.pet.service.impl;

public abstract class MergeSort extends SortStrategy {

    protected void merge(int[] array, int left, int mid, int right) {
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
    }
}
