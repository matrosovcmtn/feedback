package ru.matrosov.service;

import ru.matrosov.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAll();

    List<Review> getUserReviews(String id);

    Review create(Review model);

    Boolean delete(String id);

    Review getOne(String id);
}
