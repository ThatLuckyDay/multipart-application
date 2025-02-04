package com.pet.service;

import com.pet.model.Sorter;
import com.pet.repository.SorterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SorterService {

    private final SorterRepository sorterRepository;

    @Autowired
    public SorterService(SorterRepository sorterRepository) {
        this.sorterRepository = sorterRepository;
    }

    public List<Sorter> getAllSorters() {
        return sorterRepository.getSorters();
    }
}
