package org.example.image.wishlist;

import org.example.image.exception.ResourceNotFoundException;
import org.example.image.model.Book;
import org.example.image.model.User;
import org.example.image.model.WishList;
import org.example.image.repository.BookRepository;
import org.example.image.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final WishListRepository wishListRepository;

    public WishListService(UserRepository userRepository, BookRepository bookRepository, WishListRepository wishListRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.wishListRepository = wishListRepository;
    }

    public WishList addBookToWishList(String email, Long bookId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email :: " + email));
        bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id :: " + bookId));
        WishList wishList = new WishList();
        wishList.setUser(user);
        wishList.setBookId(bookId);
        return wishListRepository.save(wishList);
    }

    public List<WishList> getWishListByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email :: " + email));
        return user.getWishListBooks();
    }

    public void clearWishList(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email :: " + email));
        user.getWishListBooks().clear();
        userRepository.save(user);
    }
}
