package org.practice;

import org.practice.dao.PersonDao;
import org.practice.daoImpl.PersonDaoImpl;
import org.practice.model.Person;

public class ORM_Main {
    private static final PersonDao personDao = new PersonDaoImpl();

    public static void main(String[] args) {
//        Person personTest = new Person();
//        personTest.setFirstName("testF2");
//        personTest.setLastName("testL2");
//        personTest.setEmail("testE2");
//        personTest.setCode(2);
//
//        personDao.addPerson(personTest);

        personDao.getAllPersons()
                .forEach(System.out::println);
    }
}