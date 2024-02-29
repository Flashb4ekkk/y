package org.example.image.service;

import org.example.image.dto.BookDTO;
import org.example.image.model.Book;
import org.example.image.model.BookStatus;
import org.example.image.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    public Book saveBook(BookDTO book, MultipartFile image, String email) throws IOException {
        Book newBook = Book.builder()
                .title(book.getTitle())
                .image(image.getBytes())
                .description(book.getDescription())
                .author(book.getAuthor())
                .year(book.getYear())
                .publishedBy(book.getPublishedBy())
                .price(book.getPrice())
                .genres(book.getGenres())
                .user(userService.findByEmailForCheck(email).get())
                .status(BookStatus.AVAILABLE)
                .build();
        return bookRepository.save(newBook);
    }

    public Book getBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            throw new RuntimeException("Book not found");
        }
        return book;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getAllBooksById(String email) {
        Long id = userService.findIdByEmail(email);
        List<Book> books = bookRepository.findByUserId(id);
        return books;
    }

    public Book updateBook(Book book, Long id) {
        Book bookDetails = bookRepository.findById(id).orElse(null);
        if (bookDetails == null) {
            throw new RuntimeException("Book not found");
        }
        bookDetails.setTitle(book.getTitle());
        bookDetails.setImage(book.getImage());
        bookDetails.setDescription(book.getDescription());
        bookDetails.setAuthor(book.getAuthor());
        bookDetails.setYear(book.getYear());
        bookDetails.setPublishedBy(book.getPublishedBy());
        bookDetails.setPrice(book.getPrice());
        bookDetails.setGenres(book.getGenres());
        bookRepository.save(bookDetails);
        return bookDetails;
    }

    public List<Book> getAllAvailable() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .filter(book -> book.getStatus() == BookStatus.AVAILABLE)
                .collect(Collectors.toList());
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
}
