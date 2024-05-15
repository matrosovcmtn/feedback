package ru.matrosov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matrosov.dto.ReviewRateDto;
import ru.matrosov.model.ReviewRate;

@Mapper(componentModel = "spring")
public interface ReviewRateToDtoMapper {
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "review", ignore = true)
    ReviewRate fromDto(ReviewRateDto dto);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "review.id", target = "reviewId")
    ReviewRateDto toDto(ReviewRate model);
}
