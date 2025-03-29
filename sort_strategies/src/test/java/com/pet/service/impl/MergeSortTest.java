package com.pet.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortTest {

    private final MergeSort mergeSort = new MergeSortChild();

    static final class MergeSortChild extends MergeSort {

        /* For compatibility */
        @Override
        public int[] sort(int[] array) {
            return array;
        }
    }


    private static Stream<Arguments> provideMergeTestCases() {
        return Stream.of(
                Arguments.of(
                        new int[]{3, 7, 9, 2, 5, 8},
                        0,
                        2,
                        5,
                        new int[]{2, 3, 5, 7, 8, 9}
                ),

                Arguments.of(
                        new int[]{3, 3, 8, 5, 8},
                        0,
                        2,
                        4,
                        new int[]{3, 3, 5, 8, 8}
                ),

                Arguments.of(
                        new int[]{-5, 10, -3, 0},
                        0,
                        1,
                        3,
                        new int[]{-5, -3, 0, 10}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideMergeTestCases")
    void merge_ShouldCorrectlyMergeSubarrays(int[] inputArray, int left, int mid, int right, int[] expected) {
        int[] workingArray = inputArray.clone();
        mergeSort.merge(workingArray, left, mid, right);

        assertArrayEquals(expected, workingArray);
    }

    @Test
    void merge_ShouldHandleEmptyLeftSubarray() {
        int[] array = {1, 3, 5, 2, 4, 6};
        int[] expected = {1, 3, 5, 2, 4, 6};

        mergeSort.merge(array, 3, 3, 5);
        assertArrayEquals(expected, array);
    }

    @Test
    void merge_ShouldHandleSingleElementSubarrays() {
        int[] array = {5, 2};
        int[] expected = {2, 5};

        mergeSort.merge(array, 0, 0, 1);
        assertArrayEquals(expected, array);
    }

    @Test
    void merge_ShouldNotModifyOtherParts() {
        int[] array = {9, 8, 7, 4, 5, 6, 1, 2, 3};
        int[] originalPrefix = {9, 8, 7};
        int[] originalSuffix = {1, 2, 3};

        mergeSort.merge(array, 3, 5, 8);

        assertArrayEquals(originalPrefix, Arrays.copyOfRange(array, 0, 3),
                "Prefix should remain unchanged");
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, Arrays.copyOfRange(array, 3, 9),
                "Merged part should be sorted");
        assertArrayEquals(originalSuffix, Arrays.copyOfRange(array, 3, 6),
                "Suffix should remain unchanged");
    }
}