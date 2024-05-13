package ru.matrosov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matrosov.dto.CategoryCreationRequestDto;
import ru.matrosov.mapper.CategoryCreationRequestToDtoMapper;
import ru.matrosov.model.CategoryCreationRequest;
import ru.matrosov.service.CategoryCreationRequestService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categoryCreationRequest")
public class CategoryCreationRequestController {
    private final CategoryCreationRequestService categoryCreationRequestService;
    private final CategoryCreationRequestToDtoMapper categoryCreationRequestMapper;

    @PostMapping("/getAll")
    public ResponseEntity<List<CategoryCreationRequestDto>> getAll() {
        var result = categoryCreationRequestService.getAll().stream().map(categoryCreationRequestMapper::toDto).toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/getUserRequests/{id}")
    public ResponseEntity<List<CategoryCreationRequestDto>> getUserRequests(@PathVariable("id") String id) {
        var result = categoryCreationRequestMapper.toDto(categoryCreationRequestService.getUserRequests(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryCreationRequestDto> create(@RequestBody CategoryCreationRequestDto categoryCreationRequestDto) {
        var result = categoryCreationRequestMapper.toDto(categoryCreationRequestService.create(categoryCreationRequestMapper
                .fromDto(categoryCreationRequestDto)));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update")
    public ResponseEntity<CategoryCreationRequestDto> update(@RequestBody CategoryCreationRequest categoryCreationRequest) {
        var result = categoryCreationRequestMapper.toDto(categoryCreationRequestService.update(categoryCreationRequest));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        var result = categoryCreationRequestService.delete(id);
        return ResponseEntity.ok(result);
    }
}
