package ru.matrosov.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matrosov.model.Category;
import ru.matrosov.model.Review;
import ru.matrosov.repository.ReviewRepository;
import ru.matrosov.service.ReviewService;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repository;

    @Override
    public List<Review> getAll() {
        var foundReviews = repository.findAll();
        if (foundReviews.isEmpty()) {
            throw new RuntimeException("В таблице не было найдено ни одного отзыва");
        }
        return foundReviews;
    }

    @Override
    public Review getOne(String id) {
        var result = new Review();
        try {
            var optionalReview = repository.findById(id);
            if (optionalReview.isEmpty()) {
                throw new RuntimeException("Отзыв c id=[%s] не найден.".formatted(id));
            }
            result = optionalReview.get();
        } catch (Exception e) {
            throw new RuntimeException("Не смогли найти отзыв с id=[%s]. %s".formatted(id, e));
        }
        return result;
    }

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
    public Boolean delete(String id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Во время удаления отзыва с id=[%s] произошла ошибка: %s".formatted(id, e));
        }
    }
}
