package ru.matrosov.mapper;

import org.mapstruct.Mapper;
import ru.matrosov.dto.CategoryCreationRequestDto;
import ru.matrosov.model.CategoryCreationRequest;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryCreationRequestToDtoMapper {
    CategoryCreationRequest fromDto(CategoryCreationRequestDto dto);

    CategoryCreationRequestDto toDto(CategoryCreationRequest model);

    List<CategoryCreationRequestDto> toDto(List<CategoryCreationRequest> models);
}
