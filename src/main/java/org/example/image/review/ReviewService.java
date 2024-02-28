package org.example.image.review;

import org.example.image.model.Review;
import org.example.image.model.User;
import org.example.image.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Review> getReviewsByUserEmail(String email) {
        return reviewRepository.findAllByUserEmail(email);
    }

    public Review createReview(ReviewDTO review, String name) {
        Optional<User> optionalUser = userRepository.findByEmail(review.getToEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (review.getRating() < 1 || review.getRating() > 10) {
                throw new IllegalArgumentException("Rating must be between 1 and 10");
            }
            Review newReview = Review.builder()
                    .content(review.getContent())
                    .rating(review.getRating())
                    .user(user)
                    .userEmailWhoReviewed(name)
                    .build();
            user.getReviews().add(newReview);
            user.setRating((user.getRating() * user.getReviews().size() + review.getRating()) / (user.getReviews().size() + 1)); // Recalculate the average rating
            userRepository.save(user);
            return reviewRepository.save(newReview);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }


    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}

