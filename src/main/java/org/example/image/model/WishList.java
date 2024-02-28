package org.example.image.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "wishlists")
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    @JsonIdentityReference(alwaysAsId = true)
//    User user;
//
//    @OneToMany(mappedBy = "wishList")
//    @JsonManagedReference
//    List<Book> books;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    User user;

//    @ManyToOne
//    @JoinColumn(name = "book_id", nullable = false)
//    Book book;
    @Column(name = "book_id", nullable = false)
    Long bookId;
}
