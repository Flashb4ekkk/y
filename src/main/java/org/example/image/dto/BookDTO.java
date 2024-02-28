package org.example.image.dto;

import lombok.*;
import org.example.image.model.Book;
import org.example.image.model.Genre;
import org.example.image.model.Role;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String title;
    private List<Genre> genres;
    private String description;
    private String author;
    private int year;
    private String publishedBy;
    private int price;
}