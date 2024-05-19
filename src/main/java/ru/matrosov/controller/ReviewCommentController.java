package ru.matrosov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matrosov.dto.ReviewCommentDto;
import ru.matrosov.dto.ReviewDto;
import ru.matrosov.mapper.ReviewCommentToDtoMapper;
import ru.matrosov.mapper.ReviewToDtoMapper;
import ru.matrosov.model.Review;
import ru.matrosov.model.ReviewComment;
import ru.matrosov.service.ReviewCommentService;
import ru.matrosov.service.ReviewService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/review_comment")
public class ReviewCommentController {
    private final ReviewCommentService reviewCommentService;
    private final ReviewCommentToDtoMapper reviewCommentMapper;

    @GetMapping("/{id}")
    public ResponseEntity<List<ReviewCommentDto>> getAllByReview(@PathVariable("id") String id) {
        var result = reviewCommentMapper.toDto(reviewCommentService.getAllByReview(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<ReviewCommentDto> create(@RequestBody ReviewCommentDto reviewCommentDto) {
        var result = reviewCommentMapper.toDto(reviewCommentService.create(reviewCommentMapper.fromDto(reviewCommentDto)));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        var result = reviewCommentService.delete(id);
        return ResponseEntity.ok(result);
    }
}
