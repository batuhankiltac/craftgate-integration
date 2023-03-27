package com.batuhankiltac.craftgateintegrationservice.service;

import com.batuhankiltac.craftgateintegrationservice.configuration.craftgate.CraftgateConfiguration;
import com.batuhankiltac.craftgateintegrationservice.converter.creditcard.CreditCardConverter;
import com.batuhankiltac.craftgateintegrationservice.domain.CreditCard;
import com.batuhankiltac.craftgateintegrationservice.exception.CreditCardNotFoundException;
import com.batuhankiltac.craftgateintegrationservice.model.request.CreditCardRequest;
import com.batuhankiltac.craftgateintegrationservice.model.request.DeleteCardRequest;
import com.batuhankiltac.craftgateintegrationservice.repository.CreditCardRepository;
import io.craftgate.adapter.PaymentAdapter;
import io.craftgate.request.DeleteStoredCardRequest;
import io.craftgate.request.StoreCardRequest;
import io.craftgate.response.StoredCardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {
    private static final String DASH = "----";
    private static final String STATUS_EXPIRED = "EXPIRED";
    private final CreditCardConverter creditCardConverter;
    private final CreditCardRepository creditCardRepository;
    private final PaymentAdapter paymentAdapter = new PaymentAdapter(CraftgateConfiguration.getOptions());

    public void addCreditCard(CreditCardRequest creditCardRequest) {
        StoreCardRequest storeCardRequest = creditCardConverter.convert(creditCardRequest);
        log.info("storeCardRequest: {}", storeCardRequest);

        StoredCardResponse storedCardResponse = paymentAdapter.storeCard(storeCardRequest);
        log.info("storedCardResponse: {}", storedCardResponse);

        creditCardRepository.save(creditCardConverter.convertToEntity(storedCardResponse, creditCardRequest.getCardHolderName()));
    }

    public void deleteCreditCard(DeleteCardRequest deleteCardRequest) {
        DeleteStoredCardRequest deleteStoredCardRequest = creditCardConverter.convert(deleteCardRequest);
        log.info("deleteStoredCardRequest: {}", deleteStoredCardRequest);

        paymentAdapter.deleteStoredCard(deleteStoredCardRequest);

        CreditCard creditCard = creditCardRepository.findById(deleteCardRequest.getId())
                .orElseThrow(() -> new CreditCardNotFoundException("Credit Card not found.", HttpStatus.NOT_FOUND));

        creditCard.setCardToken(DASH);
        creditCard.setCardUserKey(DASH);
        creditCard.setStatus(STATUS_EXPIRED);
        creditCardRepository.save(creditCard);
    }
}
