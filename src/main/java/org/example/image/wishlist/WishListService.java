package org.example.image.wishlist;

import jakarta.transaction.Transactional;
import org.example.image.dto.WishListDTO;
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
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id :: " + bookId));
        WishList wishList = user.getWishList();
        if (wishList == null) {
            wishList = new WishList();
            wishList.setUser(user);
            wishList.setBooks(new ArrayList<>());
        }
        wishList.getBooks().add(book);
        return wishListRepository.save(wishList);
    }

//    @Transactional
    public List<Book> getWishListByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email :: " + email));
        WishList wishList = user.getWishList();
        if (wishList == null) {
            throw new ResourceNotFoundException("WishList not found for user with email :: " + email);
        }
        return new ArrayList<>(wishList.getBooks());
    }

    public void clearWishList(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email :: " + email));
        WishList wishList = user.getWishList();
        if (wishList != null) {
            wishList.getBooks().clear();
            wishListRepository.save(wishList);
        }
    }
}
