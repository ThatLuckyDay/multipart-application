package com.pet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sorter {
    private String name;
    private int capacity;
    private long duration;

}
