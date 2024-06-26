package ru.matrosov.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import ru.matrosov.enumiration.RequestStatus;

import java.time.LocalDate;

/**
 * Класс-модель заявки на создание категории
 */

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "category_creation_request")
public class CategoryCreationRequest {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Person author;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Column(name = "date")
    private LocalDate date;
}
