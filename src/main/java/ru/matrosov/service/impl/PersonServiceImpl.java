package ru.matrosov.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matrosov.model.Person;
import ru.matrosov.repository.PersonRepository;
import ru.matrosov.service.PersonService;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public List<Person> getAll() {
        var foundPeople = personRepository.findAll();
        if (foundPeople.isEmpty()) {
            throw new RuntimeException("В таблице не было найдено ни одного пользователя");
        }
        return foundPeople;
    }

    @Override
    public Person getById(String id) {
        var result = new Person();
        try {
            var optionalPerson = personRepository.findById(id);
            if (optionalPerson.isEmpty()) {
                throw new RuntimeException("Пользователь с id=[%s] не найден.".formatted(id));
            }
            result = optionalPerson.get();
        } catch (Exception e) {
            throw new RuntimeException("Не смогли найти пользователя с id=[%s]. %s".formatted(id, e));
        }
        return result;
    }

    @Transactional
    @Override
    public Person create(Person person) {
        try {
            return personRepository.save(person);
        } catch (Exception e) {
            throw new RuntimeException("Во время сохранения пользователя с id=[%s] в таблицу произошла ошибка: %s".formatted(person.getId(), e));
        }
    }

    @Transactional
    @Override
    public Person banPerson(String id) {
        var personToBan = personRepository.findById(id);
        if (personToBan.isPresent()) {
            var foundPerson = personToBan.get();
            foundPerson.setActivated(false);
            return personRepository.save(foundPerson);
        }
        throw new RuntimeException("Во время блокировки пользователя с id=[%s] произошла непревиденная ошибка".formatted(id));
    }
}
