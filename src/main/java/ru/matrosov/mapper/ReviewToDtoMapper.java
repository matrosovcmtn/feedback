package ru.matrosov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matrosov.dto.ReviewDto;
import ru.matrosov.model.Review;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewToDtoMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "author", ignore = true)
    Review fromDto(ReviewDto dto);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "author.id", target = "authorId")
    ReviewDto toDto(Review model);

    List<ReviewDto> toDto(List<Review> model);
}
