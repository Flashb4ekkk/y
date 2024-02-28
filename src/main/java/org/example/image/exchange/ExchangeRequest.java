package org.example.image.exchange;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.image.model.Book;
import org.example.image.model.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "exchange_requests")
@Builder
public class ExchangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    User requester;

    @ManyToOne
    @JoinColumn(name = "requested_book_id", nullable = false)
    Book requestedBook;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    ExchangeStatus status;
}