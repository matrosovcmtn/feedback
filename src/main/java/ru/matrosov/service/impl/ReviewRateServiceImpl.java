package ru.matrosov.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matrosov.model.ReviewRate;
import ru.matrosov.repository.ReviewRateRepository;
import ru.matrosov.service.ReviewRateService;

@Service
@AllArgsConstructor
public class ReviewRateServiceImpl implements ReviewRateService {
    private final ReviewRateRepository repository;

    @Transactional
    @Override
    public ReviewRate getOneByUserAndReview(String authorId, String reviewId) {
        var reviewRate = repository.findByAuthorIdAndReviewId(authorId, reviewId);
        if (reviewRate == null) {
            throw new RuntimeException("Оценка отзыва c id=[%s] у пользователя с id=[%s] не найден.".formatted(reviewId, authorId));
        }
        return reviewRate;
    }

    @Transactional
    @Override
    public ReviewRate create(ReviewRate reviewRate) {
        try {
            return repository.save(reviewRate);
        } catch (Exception e) {
            throw new RuntimeException("Во время сохранения оценки отзыва с id=[%s] в таблицу произошла ошибка: %s".formatted(reviewRate.getId(), e));
        }
    }

    @Transactional
    @Override
    public ReviewRate update(ReviewRate reviewRateToUpdate) {
        try {
            return repository.save(reviewRateToUpdate);
        } catch (Exception e) {
            throw new RuntimeException("Во время обновления оценки отзыва с id=[%s] произошла ошибка: %s".formatted(reviewRateToUpdate.getId(), e));
        }
    }

    @Transactional
    @Override
    public Boolean delete(String id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Во время удаления оценки отзыва с id=[%s] произошла ошибка: %s".formatted(id, e));
        }
    }
}
