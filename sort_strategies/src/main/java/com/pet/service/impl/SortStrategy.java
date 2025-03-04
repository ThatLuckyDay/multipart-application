package com.pet.service.impl;

public abstract class SortStrategy implements InplaceSort {

    protected void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
