package com.pet.service.sortImpl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void sort() {
        int[] array = {12, 11, 13, 5, 6, 7};
        int[] result = new MergeSort().sort(array);

        int[] answer = {5, 6, 7, 11, 12, 13};
        assertArrayEquals(result, answer);
        assertFalse(Arrays.equals(array, result));
    }
}