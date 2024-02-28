package org.example.image.exchange;

import lombok.Data;

@Data
public class ExchangeRequestDTO {

    private String requesterEmail; // email користувача, який зробив запит
    private Long requestedBookId; // ID книги, на яку було зроблено запи
}
