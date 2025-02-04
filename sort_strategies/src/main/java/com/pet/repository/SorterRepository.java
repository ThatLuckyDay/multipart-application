package com.pet.repository;

import com.pet.model.Sorter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SorterRepository {
    private final List<Sorter> sorters = new ArrayList<>();

    public List<Sorter> getSorters() {
        return sorters;
    }
}
