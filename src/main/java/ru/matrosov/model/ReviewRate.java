package ru.matrosov.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "review_rate")
public class ReviewRate {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.uuid.UuidGenerator.class)
    private String id;

    @JoinColumn(name = "review_id", referencedColumnName = "id")
    @ManyToOne
    private Review review;

    @Column(name = "value")
    private float value;

    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Person author;
}
