package ru.matrosov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(builderClassName = "Builder")
@Data
@NoArgsConstructor
public class ReviewCommentDto {
    private String id;
    private String reviewId;
    private String content;
    private String authorId;
    private String date;
}
