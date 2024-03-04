package org.example.image.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Table(name = "reviews")
    @Builder
    public class Review {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        Long id;
    
        @Column(name = "content", nullable = false, length = 500)
        String content;
    
        @Column(name = "rating", nullable = false)
        Double rating;
    
        @ManyToOne
        @JoinColumn(name = "reviewer_id", nullable = false)
        @JsonIdentityReference(alwaysAsId = true)
        User reviewer;
    
        @ManyToOne
        @JoinColumn(name = "reviewee_id", nullable = false)
        @JsonIdentityReference(alwaysAsId = true)
        User reviewee;
    }
