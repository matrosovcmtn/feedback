package ru.matrosov.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import ru.matrosov.enumiration.Role;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.uuid.UuidGenerator.class)
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "activated")
    private boolean activated;

    @Column(name = "activation_link")
    private String activationLink;

    @Column(name = "role")
    private Role role;
}
