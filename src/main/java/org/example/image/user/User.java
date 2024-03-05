package org.example.image.user;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.image.book.Book;
import org.example.image.review.Review;
import org.example.image.wishlist.WishList;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "username", nullable = false, length = 100)
    String username;

    @Column(name = "email", nullable = false, unique = true, length = 110)
    String email;

    @Column(name = "password", nullable = false, length = 100)
    String password;

    @Column(name = "first_name", nullable = false, length = 100)
    String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    String lastName;

    @Column(name = "mobile_phone", nullable = false)
    String mobilePhone;

    @Column(name = "rating", nullable = false)
    Double rating;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "buck", nullable = false)
    Long buck;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Book> books;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    WishList wishList;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Review> reviews;

    @Column(name = "refresh_token")
    String refreshToken;
}