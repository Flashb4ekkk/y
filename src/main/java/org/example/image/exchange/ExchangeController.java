package org.example.image.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @PostMapping("/request")
    public ResponseEntity<?> requestExchange(@RequestBody ExchangeRequestDTO request) {
        try {
            exchangeService.requestExchange(request);
            return ResponseEntity.ok("Exchange request sent successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/accept/{id}")
    public ResponseEntity<?> acceptExchange(@PathVariable Long id) {
        try {
            exchangeService.acceptExchange(id);
            return ResponseEntity.ok("Exchange request accepted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/complete/{id}")
    public ResponseEntity<?> completeExchange(@PathVariable Long id) {
        try {
            exchangeService.completeExchange(id);
            return ResponseEntity.ok("Exchange completed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<?> cancelExchange(@PathVariable Long id) {
        try {
            exchangeService.cancelExchange(id);
            return ResponseEntity.ok("Exchange cancelled successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
