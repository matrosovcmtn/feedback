package ru.matrosov.mapper;

import org.mapstruct.Mapper;
import ru.matrosov.dto.PersonDto;
import ru.matrosov.model.Person;

@Mapper(componentModel = "spring")
public interface PersonToDtoMapper {
    Person fromDto(PersonDto dto);

    PersonDto toDto(Person model);
}
