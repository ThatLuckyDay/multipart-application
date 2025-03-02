package org.practice.daoImpl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.practice.dao.PersonDao;
import org.practice.model.Person;
import org.practice.system.HibernateUtils;

import java.util.List;

public class PersonDaoImpl implements PersonDao {
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public void addPerson(Person person) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(person);

            transaction.commit();

            if (transaction.getStatus() == TransactionStatus.FAILED_COMMIT) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> persons;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
            Root<Person> root = criteriaQuery.from(Person.class);

            criteriaQuery.select(root);

            persons = session.createQuery(criteriaQuery).list();

            transaction.commit();

            if (transaction.getStatus() == TransactionStatus.FAILED_COMMIT) {
                transaction.rollback();
            }
        }
        return persons;
    }
}
