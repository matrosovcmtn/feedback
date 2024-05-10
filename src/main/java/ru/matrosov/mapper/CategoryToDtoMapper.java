package ru.matrosov.mapper;

import org.mapstruct.Mapper;
import ru.matrosov.dto.CategoryDto;
import ru.matrosov.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryToDtoMapper {
    Category fromDto(CategoryDto dto);

    CategoryDto toDto(Category model);
}
