package ru.matrosov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.matrosov.model.CategoryCreationRequest;
import ru.matrosov.model.Complaint;
import ru.matrosov.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findAllByAuthorId(String authorId);
}
