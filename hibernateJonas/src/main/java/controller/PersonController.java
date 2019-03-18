package controller;

import controller.service.PersonService;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonController {

    List<Person> people = new ArrayList<>();

    public void listPersons() {
        PersonService personService = new PersonService();
        people = personService.listPerson();
    }

    public void updatePersonMaxAge() {
        PersonService personService = new PersonService();
        Person person = findPersonWithMaxAge();
        personService.updatePersonAge(person);
    }

    private Person findPersonWithMaxAge() {
        Person person = null;
        try {
            person = people.stream()
                    .max((o1, o2) -> o1.getAge() - o2.getAge())
                    .orElseThrow(() -> new Exception("Apearently no people"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    public void deleteEveryPersonExcept() {
        PersonService personService = new PersonService();
        Person person = findPersonWithMaxAge();
        people.remove(person);
        personService.deletePersons(people);
    }
}
