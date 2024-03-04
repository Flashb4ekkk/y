package org.example.image.purchase;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/")
    @Transactional
    public ResponseEntity<?> buyBook(@RequestBody PurchaseDTO purchaseDTO) {
        purchaseService.buyBook(purchaseDTO);
        return ResponseEntity.ok("Book bought successfully, wait when the seller confirms the purchase.");
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> confirmPurchase(@PathVariable Long id , Principal principal) {
        purchaseService.confirmPurchase(id, principal.getName());
        return ResponseEntity.ok("Purchase confirmed");
    }
}
