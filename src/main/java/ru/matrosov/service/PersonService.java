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

    public List<Person> findAll() {
        return personRepository.findAll();
    }

//    public int findPersonsId(String email) {
//        return personRepository.findByEmail(email).get().getId();
//    }

    public Person update(Person person) {
        return personRepository.save(person);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }
}
