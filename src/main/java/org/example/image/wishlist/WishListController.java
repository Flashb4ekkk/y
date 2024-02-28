package org.example.image.wishlist;

import jakarta.transaction.Transactional;
import org.example.image.dto.WishListDTO;
import org.example.image.model.Book;
import org.example.image.model.WishList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping("/books/{bookId}")
    @Transactional
    public ResponseEntity<?> addBookToWishList(@PathVariable Long bookId, Principal principal) {
        wishListService.addBookToWishList(principal.getName(), bookId);
        return ResponseEntity.ok("Book added to wish list");
    }

    @GetMapping("/")
    @Transactional
    public ResponseEntity<?> getWishList(Principal principal) {
        List<Book> books = wishListService.getWishListByEmail(principal.getName());
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/")
    @Transactional
    public ResponseEntity<Void> clearWishList(Principal principal) {
        wishListService.clearWishList(principal.getName());
        return ResponseEntity.noContent().build();
    }
}
