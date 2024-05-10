package ru.matrosov.mapper;

import org.mapstruct.Mapper;
import ru.matrosov.dto.ReviewDto;
import ru.matrosov.model.Review;

@Mapper(componentModel = "spring")
public interface ReviewToDtoMapper {
    Review fromDto(ReviewDto dto);

    ReviewDto toDto(Review model);
}
