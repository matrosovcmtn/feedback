package ru.matrosov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.matrosov.model.ReviewComment;

import java.util.List;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, String> {
    List<ReviewComment> findAllByReviewId(String reviewId);
}
