package ru.matrosov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Builder(builderClassName = "Builder")
@Data
@NoArgsConstructor
public class ReviewDto {
    private String id;
    private String categoryId;
    private String authorId;
    private String title;
    private String content;
    private float reliability;
    private int usersRatedAmount;
    private LocalDate date;
    private String pictureName;
}
