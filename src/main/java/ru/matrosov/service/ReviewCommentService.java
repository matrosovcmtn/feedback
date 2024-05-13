package ru.matrosov.service;

import ru.matrosov.model.ReviewComment;

import java.util.List;

public interface ReviewCommentService {
    List<ReviewComment> getAllByReview(String id);

    ReviewComment create(ReviewComment model);

    Boolean delete(String id);
}
