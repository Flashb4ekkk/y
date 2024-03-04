package org.example.image.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.image.model.Book;
import org.example.image.model.User;
import org.example.image.purchase.PurchaseStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "purchases")
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    User buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    User seller;

    @OneToOne
    @JoinColumn(name = "book_id", nullable = false)
    Book book;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    PurchaseStatus status;
}