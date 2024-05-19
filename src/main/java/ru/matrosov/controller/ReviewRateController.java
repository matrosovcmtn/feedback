package ru.matrosov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matrosov.dto.ReviewRateDto;
import ru.matrosov.mapper.ReviewRateToDtoMapper;
import ru.matrosov.model.ReviewRate;
import ru.matrosov.service.ReviewRateService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/review_rate")
public class ReviewRateController {
    private final ReviewRateService reviewRateService;
    private final ReviewRateToDtoMapper reviewRateMapper;

    @GetMapping("/{review_id}/{author_id}")
    public ResponseEntity<ReviewRateDto> getUserRequests(@PathVariable("review_id") String reviewId, @PathVariable("author_id") String authorId) {
        var result = reviewRateMapper.toDto(reviewRateService.getOneByUserAndReview(authorId, reviewId));
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<ReviewRateDto> create(@RequestBody ReviewRateDto reviewRateDto) {
        var result = reviewRateMapper.toDto(reviewRateService.create(reviewRateMapper.fromDto(reviewRateDto)));
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<ReviewRateDto> update(@RequestBody ReviewRate reviewRate) {
        var result = reviewRateMapper.toDto(reviewRateService.update(reviewRate));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        var result = reviewRateService.delete(id);
        return ResponseEntity.ok(result);
    }
}
