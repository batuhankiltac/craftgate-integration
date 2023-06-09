package com.batuhankiltac.craftgateintegrationservice.repository;

import com.batuhankiltac.craftgateintegrationservice.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

}
