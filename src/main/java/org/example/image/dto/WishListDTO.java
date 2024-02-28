package org.example.image.dto;

import lombok.Builder;
import lombok.Data;
import org.example.image.model.Book;

import java.util.List;

@Data
@Builder
public class WishListDTO {
    List<Book> books;
}
