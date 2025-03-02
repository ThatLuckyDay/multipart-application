package org.practice.dao;

import org.practice.model.Person;

import java.util.List;

public interface PersonDao {

    void addPerson(Person person);

    List<Person> getAllPersons();

}
