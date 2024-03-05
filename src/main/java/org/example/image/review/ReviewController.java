package org.example.image.review;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<Review> createReview(@RequestBody ReviewDTO review, Principal principal) {
        return ResponseEntity.ok(reviewService.createReview(review, principal.getName()));
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<List<Review>> getReviewsByUserEmail(@PathVariable String email) {
        return ResponseEntity.ok(reviewService.getReviewsByUserEmail(email));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> getReviewsByUserEmail(@PathVariable Long id) {
        reviewService.deleteById(id);
        return ResponseEntity.ok("Review deleted successfully!");
    }
}