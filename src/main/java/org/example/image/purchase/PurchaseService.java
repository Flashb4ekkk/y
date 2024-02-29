package org.example.image.purchase;

import org.example.image.model.Book;
import org.example.image.model.BookStatus;
import org.example.image.model.User;
import org.example.image.repository.BookRepository;
import org.example.image.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public void buyBook(PurchaseDTO purchaseDTO) {
        User buyer = userRepository.findByEmail(purchaseDTO.getBuyerEmail()).orElseThrow(() -> new RuntimeException("Buyer not found"));
        User seller = userRepository.findByEmail(purchaseDTO.getSellerEmail()).orElseThrow(() -> new RuntimeException("Seller not found"));
        Book book = bookRepository.findById(purchaseDTO.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));

        if (buyer.getBuck() < book.getPrice()) {
            throw new RuntimeException("Not enough bucks");
        }
        buyer.setBuck(buyer.getBuck() - book.getPrice());
        userRepository.save(buyer);

        Purchase purchase = new Purchase();
        purchase.setBuyer(buyer);
        purchase.setSeller(seller);
        purchase.setBook(book);
        book.setStatus(BookStatus.RESERVED);
        bookRepository.save(book);
        purchase.setStatus(PurchaseStatus.RESERVED);
        purchaseRepository.save(purchase);
    }

    public void confirmPurchase(Long id, String email) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(() -> new RuntimeException("Purchase not found"));
        if (!purchase.getStatus().equals(PurchaseStatus.RESERVED)) {
            throw new RuntimeException("Purchase is not reserved");
        }
        if (!purchase.getSeller().getEmail().equals(email)) {
            throw new RuntimeException("You are not the seller");
        }
        User seller = userRepository.findById(purchase.getSeller().getId()).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(purchase.getBook().getId()).orElseThrow(() -> new RuntimeException("Book not found"));
        seller.setBuck(seller.getBuck() + book.getPrice());
        userRepository.save(seller);
        book.setStatus(BookStatus.SOLD);
        bookRepository.save(book);
        purchase.setStatus(PurchaseStatus.CONFIRMED);
        purchaseRepository.save(purchase);
    }
}
