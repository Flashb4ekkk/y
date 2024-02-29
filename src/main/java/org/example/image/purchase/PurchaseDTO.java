package org.example.image.purchase;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseDTO {
    private String buyerEmail;
    private String sellerEmail;
    private Long bookId;
}
