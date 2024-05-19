package ru.matrosov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matrosov.dto.PersonDto;
import ru.matrosov.mapper.PersonToDtoMapper;
import ru.matrosov.service.PersonService;

import java.util.List;

// todo create method, when authorization will be done

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class PersonController {
    private final PersonService personService;
    private final PersonToDtoMapper personMapper;

    @GetMapping()
    public ResponseEntity<List<PersonDto>> getAll() {
        var result = personService.getAll().stream().map(personMapper::toDto).toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getOne(@PathVariable("id") String id) {
        var result = personMapper.toDto(personService.getById(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/ban/{id}")
    public ResponseEntity<PersonDto> delete(@PathVariable("id") String id) {
        var result = personMapper.toDto(personService.banPerson(id));
        return ResponseEntity.ok(result);
    }

}
