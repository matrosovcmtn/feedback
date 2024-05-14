package ru.matrosov.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matrosov.model.Review;
import ru.matrosov.repository.ReviewRepository;
import ru.matrosov.service.ReviewService;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repository;

    @Transactional
    @Override
    public List<Review> getAll() {
        var foundReviews = repository.findAll();
        if (foundReviews.isEmpty()) {
            throw new RuntimeException("В таблице не было найдено ни одного отзыва");
        }
        return foundReviews;
    }

    @Transactional
    @Override
    public List<Review> getUserReviews(String authorId) {
        var usersReview = repository.findAllByAuthorId(authorId);
        if (usersReview.isEmpty()) {
            throw new RuntimeException("У пользователя с id=[%s] не найдено ни одного отзыва".formatted(authorId));
        }
        return usersReview;
    }

    @Transactional
    @Override
    public Review create(Review review) {
        try {
            return repository.save(review);
        } catch (Exception e) {
            throw new RuntimeException("Во время сохранения отзыва с id=[%s] в таблицу произошла ошибка: %s".formatted(review.getId(), e));
        }
    }

    @Transactional
    @Override
    public Review update(Review reviewToUpdate) {
        try {
            return repository.save(reviewToUpdate);
        } catch (Exception e) {
            throw new RuntimeException("Во время обновления отзыва с id=[%s] произошла ошибка: %s".formatted(reviewToUpdate.getId(), e));
        }
    }

    @Transactional
    @Override
    public Boolean delete(String id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Во время удаления отзыва с id=[%s] произошла ошибка: %s".formatted(id, e));
        }
    }
}
