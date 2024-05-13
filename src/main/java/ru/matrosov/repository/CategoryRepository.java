package ru.matrosov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matrosov.model.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
