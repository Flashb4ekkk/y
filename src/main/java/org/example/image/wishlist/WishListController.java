package org.example.image.wishlist;

import jakarta.transaction.Transactional;
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
    public ResponseEntity<?> addBookToWishList(@PathVariable Long bookId, Principal principal) {
        WishList wishList = wishListService.addBookToWishList(principal.getName(), bookId);
        return ResponseEntity.ok(wishList);
    }

    @GetMapping("/")
    public ResponseEntity<List<WishList>> getWishList(Principal principal) {
        List<WishList> wishList = wishListService.getWishListByEmail(principal.getName());
        return ResponseEntity.ok(wishList);
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> clearWishList(Principal principal) {
        wishListService.clearWishList(principal.getName());
        return ResponseEntity.noContent().build();
    }
}
