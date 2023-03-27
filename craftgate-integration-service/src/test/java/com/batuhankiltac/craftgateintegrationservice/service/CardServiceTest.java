package com.batuhankiltac.craftgateintegrationservice.service;

import com.batuhankiltac.craftgateintegrationservice.converter.creditcard.CreditCardConverter;
import com.batuhankiltac.craftgateintegrationservice.domain.CreditCard;
import com.batuhankiltac.craftgateintegrationservice.model.request.CreditCardRequest;
import com.batuhankiltac.craftgateintegrationservice.repository.CreditCardRepository;
import io.craftgate.adapter.PaymentAdapter;
import io.craftgate.request.StoreCardRequest;
import io.craftgate.response.StoredCardResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {

    @InjectMocks
    private CardService cardService;

    @Mock
    private CreditCardConverter creditCardConverter;

    @Mock
    private CreditCardRepository creditCardRepository;

    @Mock
    private PaymentAdapter paymentAdapter;

    @Test
    public void it_should_add_credit_card() {
        // Given
        CreditCardRequest creditCardRequest = CreditCardRequest.builder()
                .cardNumber("9792060000000007")
                .cardHolderName("test name")
                .expireMonth("11")
                .expireYear("2030")
                .build();
        StoreCardRequest storeCardRequest = StoreCardRequest.builder()
                .cardNumber(creditCardRequest.getCardNumber())
                .cardHolderName(creditCardRequest.getCardHolderName())
                .expireMonth(creditCardRequest.getExpireMonth())
                .expireYear(creditCardRequest.getExpireYear())
                .build();
        when(creditCardConverter.convert(creditCardRequest)).thenReturn(storeCardRequest);

        StoredCardResponse storedCardResponse = new StoredCardResponse();

        CreditCard creditCard = CreditCard.builder().build();
        when(creditCardConverter.convertToEntity(storedCardResponse, storeCardRequest.getCardHolderName())).thenReturn(creditCard);

        // When
        cardService.addCreditCard(creditCardRequest);

        // Then
        verify(creditCardConverter).convert(creditCardRequest);
    }
}
