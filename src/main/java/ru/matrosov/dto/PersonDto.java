package ru.matrosov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.matrosov.enumiration.Role;

@AllArgsConstructor
@Builder(builderClassName = "Builder")
@Data
@NoArgsConstructor
public class PersonDto {
    private String id;
    private String email;
    private String nickname;
    private String password;
    private boolean activated;
    private String activationLink;
    private Role role;
}
