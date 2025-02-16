package com.pet.daoImpl;

import com.pet.DataMain;
import com.pet.dao.PersonDao;
import com.pet.model.Contact;
import com.pet.model.Model;
import com.pet.model.Person;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class PersonDaoImpl implements PersonDao, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataMain.class);

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    public PersonDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private Connection getConnection() {
        Connection connection = null;
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("settings.properties")) {
            Properties props = new Properties();
            props.load(in);
            Class.forName(props.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
            connection.setAutoCommit(false);
            LOGGER.info("--------CONNECTION TO DATABASE - COMPLETE--------");
        } catch (Exception e) {
            LOGGER.info("--------CONNECTION TO DATABASE - ERROR--------");
            LOGGER.error(e.getMessage(), e);
        }
        return connection;
    }

//    @Override
//    public List<Person> findAll() {
//        List<Person> result = new ArrayList<>();
//        LOGGER.info("--------GET ALL PERSONS - START--------");
//        try (Connection connection = getConnection();
//             PreparedStatement statement = connection.prepareStatement("SELECT * FROM persons");
//             ResultSet resultSet = statement.executeQuery()) {
//            while (resultSet.next()) {
//                Person person = new Person();
//                person.setId(resultSet.getInt("id"));
//                person.setFirst_name(resultSet.getString("first_name"));
//                person.setLast_name(resultSet.getString("last_name"));
//                person.setBirthDate(resultSet.getDate("birth_date"));
//                result.add(person);
//            }
//        } catch (SQLException throwables) {
//            LOGGER.error(throwables.getMessage(), throwables);
//        }
//        LOGGER.info("--------GET ALL PERSONS - COMPLETE--------");
//        return result;
//    }

    @Override
    public List<Person> findAll() {
        String sql = "SELECT id, first_name, last_name, birth_date FROM persons";
        return this.jdbcTemplate.query(sql, (resultSet, i) -> {
            Person person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setFirst_name(resultSet.getString("first_name"));
            person.setLast_name(resultSet.getString("last_name"));
            person.setBirthDate(resultSet.getDate("birth_date"));
            return person;
        });
    }

    @Override
    public String findLastNameById(int id) {
        return this.jdbcTemplate.queryForObject("SELECT last_name FROM persons WHERE id = ?", String.class, id);
    }

    @Override
    public List<Person> findAllWithDetail() {
        String sql = "SELECT p.id, p.first_name, p.last_name, p.birth_date, c.id AS contact_id, c.person_id, " +
                "c.telephone_number, m.id AS model_id, m.model FROM persons AS p" +
                " RIGHT JOIN contacts c on p.id = c.id" +
                " RIGHT JOIN models AS m ON c.model_id = m.id;";
        return this.jdbcTemplate.query(sql, resultSet -> {
            Map<Integer, Person> personMap = new HashMap<>();
            Person person;

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                if (id == 0) {
                    id = resultSet.getInt("person_id");
                }
                person = personMap.get(id);
                if (person == null) {
                    person = new Person();
                    person.setId(id);
                    person.setFirst_name(resultSet.getString("first_name"));
                    person.setLast_name(resultSet.getString("last_name"));
                    person.setBirthDate(resultSet.getDate("birth_date"));
                    person.setContacts(new ArrayList<>());
                    personMap.put(id, person);
                }
                int contactId = resultSet.getInt("contact_id");
                if (contactId > 0) {
                    Contact contact = new Contact();
                    contact.setId(contactId);
                    contact.setPerson(personMap.get(id));
                    contact.setTelephone_number(resultSet.getString("telephone_number"));
                    contact.setModel(new Model(resultSet.getInt("model_id"),
                            resultSet.getString("model")));
                    person.getContacts().add(contact);
                }
            }
            return new ArrayList<>(personMap.values());
        });
    }

//    @Override
//    public void add(Person element) {
//        LOGGER.info("--------ADD PERSON IN A TABLE OF PERSONS - START--------");
//        try (Connection connection = getConnection();
//             PreparedStatement statement = connection.prepareStatement("INSERT INTO persons (first_name, last_name, birth_date) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
//            statement.setString(1, element.getFirst_name());
//            statement.setString(2, element.getLast_name());
//            statement.setDate(3, new java.sql.Date(element.getBirthDate().getTime()));
//            statement.executeUpdate();
//            ResultSet generatedKey = statement.getGeneratedKeys();
//            if (generatedKey.next()) {
//                element.setId(generatedKey.getInt(1));
//            }
//            connection.commit();
//        } catch (SQLException throwables) {
//            LOGGER.error(throwables.getMessage(), throwables);
//        }
//        LOGGER.info("--------ADD PERSON IN A TABLE OF PERSONS - COMPLETE--------");
//    }

    @Override
    public void add(Person person) {
        try {
            this.jdbcTemplate.update(
                    "INSERT INTO persons (first_name, last_name, birth_date) values (?, ?, ?)",
                    person.getFirst_name(), person.getLast_name(), person.getBirthDate());
            this.dataSource.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Person elem) {
        try {
            this.jdbcTemplate.update(
                    "UPDATE persons SET first_name = ?, last_name = ?, birth_date = ? WHERE id = ?",
                    elem.getFirst_name(), elem.getLast_name(), elem.getBirthDate(), elem.getId());
            this.dataSource.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void delete(int id) {
//        LOGGER.info("--------DELETE PERSON FROM A TABLE OF PERSONS - START--------");
//        try (Connection connection = getConnection();
//             PreparedStatement statement = connection.prepareStatement("DELETE FROM persons WHERE id =?")) {
//            statement.setInt(1, id);
//            statement.execute();
//            connection.commit();
//        } catch (SQLException throwables) {
//            LOGGER.error(throwables.getMessage(), throwables);
//        }
//        LOGGER.info("--------DELETE PERSON FROM A TABLE OF PERSONS - COMPETE--------");
//    }

    @Override
    public void delete(int id) {
        try {
            this.jdbcTemplate.update("DELETE FROM persons WHERE id = ?", id);
            this.dataSource.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("DataSource в классе JdbcPersonDao не должен быть равен null");
        }
    }

}