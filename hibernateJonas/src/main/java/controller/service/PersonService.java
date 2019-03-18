package controller.service;

import model.Person;
import model.Salary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PersonService {


    Session session;


    private void openTrans() {
        Configuration cfg = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Salary.class);
        SessionFactory sf = cfg.buildSessionFactory();
        session = sf.openSession();
        session.beginTransaction();
    }

    public List<Person> listPerson() {
        openTrans();
        List<Person> people = new ArrayList<>();
        people = session.createQuery("from person").list();//session.createCriteria(Person.class).list();

        printToConsole(people);
        session.disconnect();
        return people;
    }

    private <T> void printToConsole(List<T> people) {
        people.forEach(System.out::println);
    }

    public void updatePersonAge(Person person) {
        openTrans();
        person.setAge(person.getAge() * 2);
        session.update(person);
        session.getTransaction().commit();
        session.disconnect();
    }

    public void deletePersons(List<Person> people) {
        openTrans();
        people.forEach(person -> {
            if (person.getSalary() != null) {
                session.delete(person.getSalary());
            }
        });
        people.forEach(session::delete);
        session.getTransaction().commit();
        session.disconnect();
    }
}
