package com.pet.controller;

import com.pet.model.Sorter;
import com.pet.service.SorterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SorterController {

    private final SorterService sorterService;

    @Autowired
    public SorterController(SorterService sorterService) {
        this.sorterService = sorterService;
    }

    public List<Sorter> getSorters() {
        return sorterService.getAllSorters();
    }
}
