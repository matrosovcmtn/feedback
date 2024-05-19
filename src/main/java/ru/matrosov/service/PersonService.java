package ru.matrosov.service;

import ru.matrosov.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> getAll();

    Person getById(String id);

    Person create(Person person);

    Person banPerson(String id);

    Optional<Person> findByUsername(String username);

    Person getByEmail(String email);
}
