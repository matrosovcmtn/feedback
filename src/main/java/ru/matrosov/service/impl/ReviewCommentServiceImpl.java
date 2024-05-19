package ru.matrosov.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matrosov.model.ReviewComment;
import ru.matrosov.repository.ReviewCommentRepository;
import ru.matrosov.service.ReviewCommentService;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewCommentServiceImpl implements ReviewCommentService {
    private final ReviewCommentRepository repository;

    @Override
    public List<ReviewComment> getAllByReview(String reviewId) {
        var foundReviewComments = repository.findAllByReviewId(reviewId);
        if (foundReviewComments.isEmpty()) {
            throw new RuntimeException("В таблице не было найдено ни одной оценки для комментария с id=[%s]".formatted(reviewId));
        }
        return foundReviewComments;
    }

    @Transactional
    @Override
    public ReviewComment create(ReviewComment reviewComment) {
        try {
            return repository.save(reviewComment);
        } catch (Exception e) {
            throw new RuntimeException("Во время сохранения оценки комментария с id=[%s] в таблицу произошла ошибка: %s"
                    .formatted(reviewComment.getId(), e));
        }
    }

    @Transactional
    @Override
    public Boolean delete(String id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Во время удаления оценки комментария с id=[%s] произошла ошибка: %s".formatted(id, e));
        }
    }
}
