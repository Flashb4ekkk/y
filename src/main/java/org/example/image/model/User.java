package org.example.image.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
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

    @Column(name = "username", nullable = false)
    String username;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "mobile_phone", nullable = false, unique = true)
    String mobilePhone;

    @Column(name = "rating", nullable = false)
    @DecimalMin(value = "1.0", inclusive = true, message = "Rating must be equal or greater than 1.0")
    @DecimalMax(value = "10.0", inclusive = true, message = "Rating must be equal or less than 10.0")
    Double rating;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "buck", nullable = false)
    Long buck;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    Role role;

//    @OneToMany(mappedBy = "user")
//    @JsonManagedReference
////    @JsonIgnore
//    List<Book> books;
//
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    @JsonIgnore
//    WishList wishList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Book> books;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<WishList> wishListBooks;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Review> reviews;

    @Column(name = "refresh_token")
    String refreshToken;
}