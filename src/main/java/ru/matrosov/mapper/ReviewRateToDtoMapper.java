package ru.matrosov.mapper;

import org.mapstruct.Mapper;
import ru.matrosov.dto.ReviewRateDto;
import ru.matrosov.model.ReviewRate;

@Mapper(componentModel = "spring")
public interface ReviewRateToDtoMapper {
    ReviewRate fromDto(ReviewRateDto dto);

    ReviewRateDto toDto(ReviewRate model);
}
