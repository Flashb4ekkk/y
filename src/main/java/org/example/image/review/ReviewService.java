package org.example.image.review;

import org.example.image.user.User;
import org.example.image.user.UserRepository;
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
        return reviewRepository.findAllByReviewerEmail(email);
    }

    public Review createReview(ReviewDTO review, String email) {
        Optional<User> optionalReviewer = userRepository.findByEmail(email);
        Optional<User> optionalReviewee = userRepository.findByEmail(review.getRevieweeEmail());

        if (optionalReviewer.isPresent() && optionalReviewee.isPresent()) {
            User reviewer = optionalReviewer.get();
            User reviewee = optionalReviewee.get();

            if (review.getRating() < 1 || review.getRating() > 10) {
                throw new IllegalArgumentException("Rating must be between 1 and 10");
            }

            Review newReview = Review.builder()
                    .content(review.getContent())
                    .rating(review.getRating())
                    .reviewer(reviewer)
                    .reviewee(reviewee)
                    .build();

            reviewee.getReviews().add(newReview);
            reviewee.setRating((reviewee.getRating() * reviewee.getReviews().size() + review.getRating()) / (reviewee.getReviews().size() + 1)); // Recalculate the average rating

            reviewee.getReviews().remove(newReview); // Remove the review from the reviewee's list of reviews
            userRepository.save(reviewer);
            userRepository.save(reviewee);

            return reviewRepository.save(newReview);
        } else {
            throw new IllegalArgumentException("Reviewer or reviewee not found");
        }
    }



    public void deleteById(Long id) {
        if(!reviewRepository.existsById(id)){
            throw new IllegalArgumentException("Review with id " + id + " not found");
        }
        reviewRepository.deleteById(id);
    }
}

