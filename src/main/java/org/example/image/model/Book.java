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
@Table(name = "books")
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "title", nullable = false)
    String title;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @ElementCollection(targetClass = Genre.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "book_genre")
    @Column(name = "genre")
    private List<Genre> genres;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "author", nullable = false)
    String author;

    @Column(name = "year", nullable = false)
    int year;

    @Column(name = "published_by", nullable = false)
    String publishedBy;

    @Column(name = "price", nullable = false)
    int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    BookStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnore
    User user;

//    @ManyToOne
//    @JoinColumn(name = "wish_list_id")
//    @JsonBackReference
//    WishList wishList;
}