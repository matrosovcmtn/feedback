package ru.matrosov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matrosov.dto.CategoryDto;
import ru.matrosov.mapper.CategoryToDtoMapper;
import ru.matrosov.model.Category;
import ru.matrosov.service.CategoryService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryToDtoMapper categoryMapper;

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAll() {
        var result = categoryService.getAll().stream().map(categoryMapper::toDto).toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getOne(@PathVariable("id") String id) {
        var result = categoryMapper.toDto(categoryService.getById(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        var result = categoryMapper.toDto(categoryService.create(categoryMapper.fromDto(categoryDto)));
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<CategoryDto> update(@RequestBody Category category) {
        var result = categoryMapper.toDto(categoryService.update(category));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        var result = categoryService.delete(id);
        return ResponseEntity.ok(result);
    }
}
