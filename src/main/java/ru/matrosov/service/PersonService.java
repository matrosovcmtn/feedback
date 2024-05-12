package ru.matrosov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.matrosov.model.Person;
import ru.matrosov.repository.PersonRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(String id) {
        var optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            return optionalPerson.get();
        }
        throw new RuntimeException("User with id=[%s] is not found".formatted(id));
    }

    public Person create(Person person) {
        try {
            return personRepository.save(person);
        } catch (Exception e) {
            throw new RuntimeException("Во время сохранения пользователя [%s] в таблицу произошла ошибка: ".formatted(person.getId()) + e);
        }
    }

    public Person banPerson(int id) {
        var personToBan = personRepository.findById(id);
        if (personToBan.isPresent()) {
            var foundPerson = personToBan.get();
            foundPerson.setActivated(false);
            return personRepository.save(foundPerson);
        }
        throw new RuntimeException("Во время блокировки пользователя произошла непревиденная ошибка");
    }
}
