package com.pet;

import com.pet.dao.PersonDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_context.xml");

        PersonDao personDao = context.getBean("personDao", PersonDao.class);

        System.out.println("Last name person (have id = 1) is - " + personDao.findLastNameById(1));
    }
}
