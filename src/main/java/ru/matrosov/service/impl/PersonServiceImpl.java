package ru.matrosov.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matrosov.model.Person;
import ru.matrosov.repository.PersonRepository;
import ru.matrosov.service.PersonService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

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
        var personToCreate = new Person();
        var savedPerson = new Person();
        try {
            personToCreate.setNickname(person.getNickname());
            personToCreate.setEmail(person.getEmail());
            personToCreate.setPassword(passwordEncoder.encode(person.getPassword()));
            personToCreate.setNickname(person.getNickname());
            personToCreate.setRole(person.getRole());
            savedPerson = personRepository.save(personToCreate);
        } catch (Exception e) {
            throw new RuntimeException("Во время сохранения пользователя возникла ошибка: %s".formatted(e));
        }
        return savedPerson;
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

    @Override
    public Optional<Person> findByUsername(String nickname) {
        try {
            var optionalPerson = personRepository.findByEmail(nickname);
            if (optionalPerson.isEmpty()) {
                throw new RuntimeException("Пользователь с nickname=[%s] не найден.".formatted(nickname));
            }
            return optionalPerson;
        } catch (Exception e) {
            throw new RuntimeException("Не смогли найти пользователя с nickname=[%s]. %s".formatted(nickname, e));
        }
    }

    @Override
    public Person getByEmail(String email) {
        var result = new Person();
        try {
            var optionalPerson = personRepository.findByEmail(email);
            if (optionalPerson.isEmpty()) {
                throw new RuntimeException("Пользователь с email=[%s] не найден.".formatted(email));
            }
            result = optionalPerson.get();
        } catch (Exception e) {
            throw new RuntimeException("Не смогли найти пользователя с email=[%s]. %s".formatted(email, e));
        }
        return result;
    }
}
