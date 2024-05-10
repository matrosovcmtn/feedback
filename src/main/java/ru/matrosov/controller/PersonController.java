package ru.matrosov.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.matrosov.dto.PersonDTO;
import ru.matrosov.model.Person;
import ru.matrosov.service.PersonService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/people")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {
    private final PersonService personService;
//    private final PersonMapper personMapper; //todo mapper

//    @PostMapping("/findAll")
//    public List<PersonDTO> findAll() {
//        return personService.findAll().stream().map(o -> personMapper.toDto(o)).toList();
//    }
//
//    @PostMapping("/find/{id}")
//    public PersonDTO findOne(@PathVariable("id") int id) {
//        return personService.findOne(id);
//    }

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
