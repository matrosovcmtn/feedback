package ru.matrosov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matrosov.dto.ComplaintDto;
import ru.matrosov.model.Complaint;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComplaintToDtoMapper {
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "target", ignore = true)
    @Mapping(target = "review", ignore = true)
    Complaint fromDto(ComplaintDto dto);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "review.id", target = "reviewId")
    @Mapping(source = "target.id", target = "targetId")
    ComplaintDto toDto(Complaint model);

    List<ComplaintDto> toDto(List<Complaint> model);
}
