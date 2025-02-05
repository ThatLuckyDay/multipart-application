package com.pet.service.sortImpl;


import com.pet.service.InplaceSort;
import org.springframework.stereotype.Component;

@Component
public class MergeSort implements InplaceSort {

    @Override
    public int[] sort(int[] values) {
        if (values == null || values.length <= 1) {
            return values;
        }

        int n = values.length;

        int[] result = new int[n];
        System.arraycopy(values, 0, result, 0, n);

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
