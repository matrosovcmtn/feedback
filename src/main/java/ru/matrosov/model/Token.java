package ru.matrosov.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "token")
public class Token {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @ManyToOne
    private Person person;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "activated")
    private boolean activated;

    @Column(name = "authentication_link")
    private String authenticationLink;
}
