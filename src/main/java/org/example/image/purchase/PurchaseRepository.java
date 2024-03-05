package org.example.image.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// PurchaseRepository.java
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}

