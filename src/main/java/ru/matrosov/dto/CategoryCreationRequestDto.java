package ru.matrosov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.matrosov.enumiration.RequestStatus;
import ru.matrosov.model.Person;

import java.time.LocalDate;

@AllArgsConstructor
@Builder(builderClassName = "Builder")
@Data
@NoArgsConstructor
public class CategoryCreationRequestDto {
    private String id;
    private Person author;
    private String title;
    private String content;
    private LocalDate date;
    private RequestStatus status;
}
