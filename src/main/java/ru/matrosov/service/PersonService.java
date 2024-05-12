package ru.matrosov.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.matrosov.dto.PersonDto;
import ru.matrosov.model.Person;
import ru.matrosov.repository.PersonRepository;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(String id) {
        var optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            return optionalPerson.get();
        }
        throw new RuntimeException("User with id=[%s] is not found".formatted(id));
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public Person create(Person person) {
        try {
            return personRepository.save(person);
        } catch (Exception e) {
            throw new RuntimeException("Во время сохранения пользователя [%s] в таблицу произошла ошибка: ".formatted(person.getId()) + e);
        }
    }
}
