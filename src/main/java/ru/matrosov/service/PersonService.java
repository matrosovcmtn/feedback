package ru.matrosov.service;

import ru.matrosov.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAll();

    Person getById(String id);

    Person create(Person person);

    Person banPerson(String id);
}
