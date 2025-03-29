package com.pet.service.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SortStrategyTest {

    @ParameterizedTest
    @ValueSource(classes = {MergeSortIterative.class, QuickSortRecursive.class})
    void swapMethod_SwapsElements(Class<? extends SortStrategy> clazz) throws Exception {
        SortStrategy strategy = clazz.getDeclaredConstructor().newInstance();
        int[] array = {1, 2};

        strategy.swap(array, 0, 1);

        assertArrayEquals(new int[]{2, 1}, array);
    }

}