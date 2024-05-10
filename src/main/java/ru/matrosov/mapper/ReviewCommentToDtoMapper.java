package ru.matrosov.mapper;

import org.mapstruct.Mapper;
import ru.matrosov.dto.ReviewCommentDto;
import ru.matrosov.model.ReviewComment;

@Mapper(componentModel = "spring")
public interface ReviewCommentToDtoMapper {
    ReviewComment fromDto(ReviewCommentDto dto);

    ReviewCommentDto toDto(ReviewComment model);
}
