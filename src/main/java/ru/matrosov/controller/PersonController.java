package ru.matrosov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matrosov.dto.PersonDto;
import ru.matrosov.mapper.PersonToDtoMapper;
import ru.matrosov.service.PersonService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final PersonToDtoMapper personMapper;

    @PostMapping("/getAll")
    public ResponseEntity<List<PersonDto>> getAll() {
        var result = personService.getAll().stream().map(personMapper::toDto).toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/get/{id}")
    public ResponseEntity<PersonDto> getOne(@PathVariable("id") String id) {
        var result = personMapper.toDto(personService.getById(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create") // todo delete method, when authorization will be done
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto personDto) {
        var result = personMapper.toDto(personService.create(personMapper.fromDto(personDto)));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/ban/{id}")
    public ResponseEntity<PersonDto> delete(@PathVariable("id") int id) {
        var result = personMapper.toDto(personService.banPerson(id));
        return ResponseEntity.ok(result);
    }

}
