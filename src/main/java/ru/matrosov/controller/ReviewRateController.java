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
@RequestMapping("/reviewRate")
public class ReviewRateController {
    private final ReviewRateService reviewRateService;
    private final ReviewRateToDtoMapper reviewRateMapper;

    @PostMapping("/getOneByUserAndReview/{id}")
    public ResponseEntity<ReviewRateDto> getUserRequests(@PathVariable("id") String authorId, @PathVariable("id") String reviewId) {
        var result = reviewRateMapper.toDto(reviewRateService.getOneByUserAndReview(authorId, reviewId));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<ReviewRateDto> create(@RequestBody ReviewRateDto reviewRateDto) {
        var result = reviewRateMapper.toDto(reviewRateService.create(reviewRateMapper.fromDto(reviewRateDto)));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update")
    public ResponseEntity<ReviewRateDto> update(@RequestBody ReviewRate reviewRate) {
        var result = reviewRateMapper.toDto(reviewRateService.update(reviewRate));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        var result = reviewRateService.delete(id);
        return ResponseEntity.ok(result);
    }
}
