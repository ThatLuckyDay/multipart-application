package com.pet.dao;

import com.pet.model.Person;

import java.util.List;

public interface PersonDao extends SimpleAction<Person> {
    List<Person> findAll();

    String findLastNameById(int id);

    List<Person> findAllWithDetail();

}
