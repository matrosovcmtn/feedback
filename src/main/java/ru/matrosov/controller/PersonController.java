package ru.matrosov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matrosov.dto.PersonDto;
import ru.matrosov.mapper.PersonToDtoMapper;
import ru.matrosov.model.Person;
import ru.matrosov.service.PersonService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/people")
//@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {
    private final PersonService personService;
    private final PersonToDtoMapper personMapper;

    @PostMapping("/findAll")
    public List<PersonDto> findAll() {
        return personService.findAll().stream().map(personMapper::toDto).toList();
    }

    @PostMapping("/find/{id}")
    public PersonDto findOne(@PathVariable("id") String id) {
        return personMapper.toDto(personService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto personDto) {
        var created = personService.create(personMapper.fromDto(personDto));
        return ResponseEntity.ok(personMapper.toDto(created));
    }

    /**
     * Обновление информации о пользователе
     */
    @PutMapping
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    /**
     * Удаление пользователя
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        personService.delete(id);
    }

}
