package com.pet;

import com.pet.dao.PersonDao;
import com.pet.model.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class DataMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_context.xml");

        PersonDao personDao = context.getBean("personDao", PersonDao.class);

        System.out.println("\nSIMPLE PERSONS:\n");

        personDao.findAll().forEach(System.out::println);

        System.out.println("\nPERSONS WITH DETAILS\n");

        personDao.findAllWithDetail().forEach(System.out::println);

        Person personNew = new Person();
        personNew.setFirst_name("Mike");
        personNew.setLast_name("Petrov");
        personNew.setBirthDate(new Date(System.currentTimeMillis()));
        personDao.add(personNew);
        System.out.println("\nAdd new person\n");
        personDao.findAll().forEach(System.out::println);

        Person aimPerson = null;
        System.out.println("Last name person with id = 1 is - " + personDao.findLastNameById(1));
        for (Person person : personDao.findAllWithDetail()) {
            if (person.getId() == 1) {
                aimPerson = person;
            }
        }
        System.out.println("\nUpdate last name for person with id = 1.\n");
        assert aimPerson != null;
        aimPerson.setLast_name("Ivanov");
        personDao.update(aimPerson);
        System.out.println("Last name person with id = 1 is - " + personDao.findLastNameById(1));

        personDao.delete(1);
        System.out.println("\nDelete person with id = 1\n");
        personDao.findAll().forEach(System.out::println);

    }
}
