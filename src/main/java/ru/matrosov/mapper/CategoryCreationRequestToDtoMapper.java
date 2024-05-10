package ru.matrosov.mapper;

import org.mapstruct.Mapper;
import ru.matrosov.dto.CategoryCreationRequestDto;
import ru.matrosov.dto.CategoryDto;
import ru.matrosov.model.Category;
import ru.matrosov.model.CategoryCreationRequest;

@Mapper(componentModel = "spring")
public interface CategoryCreationRequestToDtoMapper {
    CategoryCreationRequest fromDto(CategoryCreationRequestDto dto);

    CategoryCreationRequestDto toDto(CategoryCreationRequest model);
}
