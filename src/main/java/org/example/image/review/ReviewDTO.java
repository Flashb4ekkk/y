package org.example.image.review;

import lombok.Builder;
import lombok.Data;
import org.example.image.model.Review;

@Data
@Builder
public class ReviewDTO {
    private String content;
    private double rating;
    private String revieweeEmail;
}