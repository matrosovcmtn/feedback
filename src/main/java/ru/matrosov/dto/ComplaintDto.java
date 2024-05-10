package ru.matrosov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.matrosov.model.Person;

import java.time.LocalDate;

@AllArgsConstructor
@Builder(builderClassName = "Builder")
@Data
@NoArgsConstructor
public class ComplaintDto {
    private Person author;
    private Person target;
    private String review;
    private String reviewId;
    private String content;
    private String status;
    private LocalDate date;
}
