package org.example.image.user;

import lombok.Builder;
import lombok.Data;
import org.example.image.book.Book;
import org.example.image.review.Review;

import java.util.List;

@Data
@Builder
public class UserDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private Double rating;
    private Long buck;
    private Role role;
    private List<Book> books;
    private List<Review> reviews;
}
