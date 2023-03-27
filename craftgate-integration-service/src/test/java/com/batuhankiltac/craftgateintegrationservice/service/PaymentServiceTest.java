package com.batuhankiltac.craftgateintegrationservice.service;

import com.batuhankiltac.craftgateintegrationservice.converter.payment.PaymentConverter;
import com.batuhankiltac.craftgateintegrationservice.model.AddressDetail;
import com.batuhankiltac.craftgateintegrationservice.model.BasketItem;
import com.batuhankiltac.craftgateintegrationservice.model.BuyerDetail;
import com.batuhankiltac.craftgateintegrationservice.model.CardDetail;
import com.batuhankiltac.craftgateintegrationservice.model.enums.PaymentChannel;
import com.batuhankiltac.craftgateintegrationservice.model.request.CreditCardRequest;
import com.batuhankiltac.craftgateintegrationservice.model.request.PaymentRequest;
import com.batuhankiltac.craftgateintegrationservice.publisher.PaymentPublisher;
import io.craftgate.adapter.PaymentAdapter;
import io.craftgate.model.Currency;
import io.craftgate.request.CreatePaymentRequest;
import io.craftgate.request.dto.Card;
import io.craftgate.request.dto.PaymentItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentConverter paymentConverter;

    @Mock
    private PaymentPublisher paymentPublisher;

    @Mock
    private CardService cardService;

    @Mock
    private PaymentAdapter paymentAdapter;

    @Test
    public void it_should_pay() {
        // Given
        BasketItem basketItem = BasketItem.builder()
                .itemId("12")
                .basketItemTransactionId("1344")
                .price(BigDecimal.TEN)
                .paidPrice(BigDecimal.TEN)
                .build();
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .totalPrice(BigDecimal.TEN)
                .paidPrice(BigDecimal.TEN)
                .installmentCount(12)
                .installmentNo(5)
                .cardDetail(CardDetail.builder()
                                    .cardHolderName("test-name")
                                    .cardNumber("9792060000000007")
                                    .expireMonth("10")
                                    .expireYear("2030")
                                    .cvc("123")
                                    .build())
                .buyer(BuyerDetail.builder()
                               .id("12")
                               .name("test-name")
                               .surname("test-surname")
                               .identityNumber("123214324234")
                               .email("test@test.com")
                               .build())
                .billingAddress(AddressDetail.builder()
                                        .address("test-address")
                                        .city("test-city")
                                        .country("test-country")
                                        .build())
                .basketItems(Collections.singletonList(basketItem))
                .paymentChannel(PaymentChannel.WEB)
                .build();
        CreatePaymentRequest createPaymentRequest = CreatePaymentRequest.builder()
                .price(paymentRequest.getTotalPrice())
                .paidPrice(paymentRequest.getPaidPrice())
                .walletPrice(BigDecimal.ZERO)
                .installment(paymentRequest.getInstallmentCount())
                .card(Card.builder()
                              .cardHolderName("test-name")
                              .cardNumber("9792060000000007")
                              .expireMonth("10")
                              .expireYear("2030")
                              .cvc("123")
                              .build())
                .items(Collections.singletonList(PaymentItem.builder()
                                                         .price(BigDecimal.TEN)
                                                         .build()))
                .paymentChannel(paymentRequest.getPaymentChannel().name())
                .currency(Currency.TRY)
                .build();
        when(paymentConverter.convert(paymentRequest)).thenReturn(createPaymentRequest);

        // When
        paymentService.pay(paymentRequest);

        // Then
        verify(paymentConverter).convert(paymentRequest);
        verify(cardService).addCreditCard(CreditCardRequest.builder()
                                                  .cardHolderName(paymentRequest.getCardDetail().getCardHolderName())
                                                  .cardNumber(paymentRequest.getCardDetail().getCardNumber())
                                                  .expireMonth(paymentRequest.getCardDetail().getExpireMonth())
                                                  .expireYear(paymentRequest.getCardDetail().getExpireYear())
                                                  .build());
    }
}
