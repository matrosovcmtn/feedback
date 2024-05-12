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
public class ReviewDto {
    private String id;
    private String category;
    private String categoryId;
    private Person author;
    private String title;
    private String content;
    private String reliability;
    private String usersRatedAmount;
    private LocalDate date;
    private String pictureName;
}
