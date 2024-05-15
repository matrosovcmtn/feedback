package ru.matrosov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matrosov.dto.CategoryDto;
import ru.matrosov.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryToDtoMapper {
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "request", ignore = true)
    Category fromDto(CategoryDto dto);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "request.id", target = "requestId")
    CategoryDto toDto(Category model);
}
