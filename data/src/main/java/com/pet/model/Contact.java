package com.pet.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private int id;
    private Model model;
    private String telephone_number;

    @ToString.Exclude
    private Person person;
}
