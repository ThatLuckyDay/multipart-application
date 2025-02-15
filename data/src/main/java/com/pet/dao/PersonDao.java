package com.pet.dao;

import com.pet.model.Person;

import java.util.List;

public interface PersonDao extends SimpleAction<Person> {
    List<Person> findAll();

    List<Person> findByFirstName(String firstName);

    String findFirstNameById(int id);

    String findLastNameById(int id);
}
