package ru.matrosov.service;

import ru.matrosov.model.ReviewRate;

public interface ReviewRateService {
    ReviewRate getOneByUserAndReview(String authorId, String reviewId);

    ReviewRate create(ReviewRate model);

    ReviewRate update(ReviewRate model);

    Boolean delete(String id);
}
