package ru.matrosov.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import ru.matrosov.enumiration.RequestStatus;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "complaint")
public class Complaint {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.uuid.UuidGenerator.class)
    private String id;

    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Person author;

    @JoinColumn(name = "target_id", referencedColumnName = "id")
    @ManyToOne
    private Person target;

    @JoinColumn(name = "review_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private Review review;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private RequestStatus status;

    @Column(name = "date")
    private LocalDate date;
}
