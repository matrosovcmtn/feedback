package ru.matrosov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matrosov.dto.ReviewDto;
import ru.matrosov.mapper.ReviewToDtoMapper;
import ru.matrosov.model.Review;
import ru.matrosov.service.ReviewService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewToDtoMapper reviewMapper;

    @PostMapping("/getAll")
    public ResponseEntity<List<ReviewDto>> getAll() {
        var result = reviewService.getAll().stream().map(reviewMapper::toDto).toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/getUserReviews/{id}")
    public ResponseEntity<List<ReviewDto>> getUserRequests(@PathVariable("id") String id) {
        var result = reviewMapper.toDto(reviewService.getUserReviews(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<ReviewDto> create(@RequestBody ReviewDto reviewDto) {
        var result = reviewMapper.toDto(reviewService.create(reviewMapper.fromDto(reviewDto)));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update")
    public ResponseEntity<ReviewDto> update(@RequestBody Review review) {
        var result = reviewMapper.toDto(reviewService.update(review));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        var result = reviewService.delete(id);
        return ResponseEntity.ok(result);
    }
}
