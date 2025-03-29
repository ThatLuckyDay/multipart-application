package com.pet.service.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortIterativeTest {
    private final InplaceSort sorter = new MergeSortIterative();

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(new int[]{}, new int[]{}),
                Arguments.of(new int[]{5}, new int[]{5}),
                Arguments.of(new int[]{3, 7, 9, 2, 5, 8}, new int[]{2, 3, 5, 7, 8, 9}),
                Arguments.of(new int[]{-1, 5, 25, 7, 5, 10}, new int[]{-1, 5, 5, 7, 10, 25})
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    void testSort(int[] input, int[] expected) {
        int[] result = sorter.sort(input.clone());
        assertArrayEquals(expected, result);
    }

}