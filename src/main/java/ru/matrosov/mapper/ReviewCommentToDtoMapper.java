package ru.matrosov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matrosov.dto.ReviewCommentDto;
import ru.matrosov.model.ReviewComment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewCommentToDtoMapper {
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "review", ignore = true)
    ReviewComment fromDto(ReviewCommentDto dto);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "review.id", target = "reviewId")
    ReviewCommentDto toDto(ReviewComment model);

    List<ReviewCommentDto> toDto(List<ReviewComment> model);
}
