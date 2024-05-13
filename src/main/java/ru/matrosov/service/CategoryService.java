package ru.matrosov.service;

import ru.matrosov.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Category getById(String id);

    Category create(Category model);

    Category update(Category model);

    Boolean delete(String id);
}
