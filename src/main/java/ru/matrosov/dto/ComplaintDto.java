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
public class ComplaintDto {
    private String id;
    private String authorId;
    private String targetId;
    private String reviewId;
    private String content;
    private String status;
    private LocalDate date;
}
