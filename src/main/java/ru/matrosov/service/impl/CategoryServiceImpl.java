package ru.matrosov.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matrosov.model.Category;
import ru.matrosov.repository.CategoryRepository;
import ru.matrosov.service.CategoryService;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public List<Category> getAll() {
        var foundCategories = categoryRepository.findAll();
        if (foundCategories.isEmpty()) {
            throw new RuntimeException("В таблице не было найдено ни одной категории");
        }
        return foundCategories;
    }

    @Transactional
    @Override
    public Category getById(String id) {
        var result = new Category();
        try {
            var optionalCategory = categoryRepository.findById(id);
            if (optionalCategory.isEmpty()) {
                throw new RuntimeException("Категория не найдена.");
            }
            result = optionalCategory.get();
        } catch (Exception e) {
            throw new RuntimeException("Не смогли найти категорию с id=[%s]. %s".formatted(id, e));
        }
        return result;
    }

    @Transactional
    @Override
    public Category create(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new RuntimeException("Во время сохранения категории с id=[%s] в таблицу произошла ошибка: %s".formatted(category.getId(), e));
        }
    }

    @Transactional
    @Override
    public Category update(Category categoryToUpdate) {
        try {
            return categoryRepository.save(categoryToUpdate);
        } catch (Exception e) {
            throw new RuntimeException("Во время обновления категории с id=[%s] произошла ошибка: %s".formatted(categoryToUpdate.getId(), e));
        }
    }

    @Transactional
    @Override
    public Boolean delete(String id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Во время удаления категории с id=[%s] произошла ошибка: %s".formatted(id, e));
        }
    }
}
