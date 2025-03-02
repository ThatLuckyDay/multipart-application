package org.practice.system;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.practice.model.Model;
import org.practice.model.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HibernateUtils {

    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        if (sessionFactory != null) {
            return sessionFactory;
        }

        Properties properties = new Properties();
        try (InputStream inputStream = HibernateUtils.class
                .getClassLoader()
                .getResourceAsStream("hibernate.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load hibernate.properties", e);
        }

        return new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Model.class)
                .buildSessionFactory();
    }

}
