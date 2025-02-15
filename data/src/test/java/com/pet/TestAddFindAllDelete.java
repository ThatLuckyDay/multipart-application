package com.pet;

import com.pet.dao.PersonDao;
import com.pet.daoImpl.PersonDaoImpl;
import com.pet.model.Person;

import java.util.Date;
import java.util.GregorianCalendar;

public class TestAddFindAllDelete {
    private static final PersonDao personDao = new PersonDaoImpl();

    public static void main(String[] args) {
        System.out.println("Начальный список людей");
        listAllPerson();
        System.out.println("-----------------");
        System.out.println("Вставка нового человека в БД");
        Person person = new Person();
        person.setFirst_name("Tom");
        person.setLast_name("Smith");
        person.setBirthDate(new Date(
                (new GregorianCalendar(2001,10, 1)).getTime().getTime()));
        personDao.add(person);
        listAllPerson();
        System.out.println("-----------------");
        System.out.println("Удаление созданного человека из БД");
        personDao.delete(person.getId());
        listAllPerson();
    }

    private static void listAllPerson() {
        for (Person person : personDao.findAll()) {
            System.out.println(person);
        }
    }
}
