package org.example.image.wishlist;

import lombok.Builder;
import lombok.Data;
import org.example.image.book.Book;

import java.util.List;

@Data
@Builder
public class WishListDTO {
    List<Book> books;
}
