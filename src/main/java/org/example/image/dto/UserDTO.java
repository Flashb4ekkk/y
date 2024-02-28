package org.example.image.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.example.image.model.Book;
import org.example.image.model.Review;
import org.example.image.model.Role;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
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
