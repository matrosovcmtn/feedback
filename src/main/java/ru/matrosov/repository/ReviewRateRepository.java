package ru.matrosov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.matrosov.model.ReviewRate;

@Repository
public interface ReviewRateRepository extends JpaRepository<ReviewRate, String> {
    ReviewRate findByAuthorIdAndReviewId(String authorId, String reviewId);
}
