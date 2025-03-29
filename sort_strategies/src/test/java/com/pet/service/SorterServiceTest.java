package com.pet.service;

import com.pet.model.Sorter;
import com.pet.repository.SorterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SorterServiceTest {
    @MockBean
    private SorterRepository mockRepo;

    @Autowired
    private SorterService service;

    @Test
    void addSortedByMergeSortIterative_SavesSorterToRepository() {
        List<Sorter> sorters = new ArrayList<>();
        int[] array = {5, 3, 1};

        when(mockRepo.getSorters()).thenReturn(sorters);

        service.addSortedByMergeSortIterative(array);

        verify(mockRepo, times(1)).getSorters();

        Sorter result = mockRepo.getSorters().getFirst();

        assertAll(
                () -> assertEquals(1, sorters.size()),
                () -> assertEquals(array.length, result.getCapacity()),
                () -> assertTrue(result.getDuration() > 0),
                () -> assertTrue(result.getName().contains("MergeSortIterative"))
        );
    }
}