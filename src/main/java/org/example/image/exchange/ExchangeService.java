package org.example.image.exchange;

import org.example.image.model.Book;
import org.example.image.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private BookRepository bookRepository;

    public void requestExchange(ExchangeRequestDTO request) {
        // Validate the request and create a new ExchangeRequest
        ExchangeRequest exchangeRequest = new ExchangeRequest();
//        exchangeRequest.setRequester(request.getRequesterEmail());
//        exchangeRequest.setRequestedBook(request.getRequestedBookId());
        exchangeRequest.setStatus(ExchangeStatus.PENDING);

        // Save the ExchangeRequest in the repository
        exchangeRepository.save(exchangeRequest);
    }

    public void acceptExchange(Long id) {
        // Find the ExchangeRequest in the repository
        ExchangeRequest exchangeRequest = exchangeRepository.findById(id).orElseThrow(() -> new RuntimeException("ExchangeRequest not found"));

        // Set the status to ACCEPTED
        exchangeRequest.setStatus(ExchangeStatus.ACCEPTED);

        // Save the ExchangeRequest in the repository
        exchangeRepository.save(exchangeRequest);
    }

    public void completeExchange(Long id) {
        // Find the ExchangeRequest in the repository
        ExchangeRequest exchangeRequest = exchangeRepository.findById(id).orElseThrow(() -> new RuntimeException("ExchangeRequest not found"));

        // Set the status to COMPLETED
//        exchangeRequest.setStatus(ExchangeStatus.COMPLETED);

        // Update the owner of the book
        Book book = bookRepository.findById(exchangeRequest.getRequestedBook().getId()).orElseThrow(() -> new RuntimeException("Book not found"));
//        book.setOwner(exchangeRequest.getRequester());

        // Save the ExchangeRequest and the Book in the repository
        exchangeRepository.save(exchangeRequest);
        bookRepository.save(book);
    }

    public void cancelExchange(Long id) {
        // Find the ExchangeRequest in the repository
        ExchangeRequest exchangeRequest = exchangeRepository.findById(id).orElseThrow(() -> new RuntimeException("ExchangeRequest not found"));

        // Set the status to CANCELLED
//        exchangeRequest.setStatus(ExchangeStatus.CANCELLED);

        // Save the ExchangeRequest in the repository
        exchangeRepository.save(exchangeRequest);
    }
}
