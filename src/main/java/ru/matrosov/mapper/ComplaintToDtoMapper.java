package ru.matrosov.mapper;

import org.mapstruct.Mapper;
import ru.matrosov.dto.ComplaintDto;
import ru.matrosov.model.Complaint;

@Mapper(componentModel = "spring")
public interface ComplaintToDtoMapper {
    Complaint fromDto(ComplaintDto dto);

    ComplaintDto toDto(Complaint model);
}
