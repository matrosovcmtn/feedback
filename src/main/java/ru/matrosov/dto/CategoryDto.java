package ru.matrosov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(builderClassName = "Builder")
@Data
@NoArgsConstructor
public class CategoryDto {
    private String id;
    private String title;
    private String pictureName;
    private String requestId;
    private String authorId;
}
