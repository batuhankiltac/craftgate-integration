package com.batuhankiltac.craftgateintegrationservice.converter.creditcard;

import com.batuhankiltac.craftgateintegrationservice.domain.CreditCard;
import com.batuhankiltac.craftgateintegrationservice.model.CardDetail;
import com.batuhankiltac.craftgateintegrationservice.model.enums.CardAssociation;
import com.batuhankiltac.craftgateintegrationservice.model.enums.CardType;
import com.batuhankiltac.craftgateintegrationservice.model.request.CreditCardRequest;
import com.batuhankiltac.craftgateintegrationservice.model.request.DeleteCardRequest;
import io.craftgate.request.DeleteStoredCardRequest;
import io.craftgate.request.StoreCardRequest;
import io.craftgate.request.dto.Card;
import io.craftgate.response.StoredCardResponse;
import org.springframework.stereotype.Component;

@Component
public class CreditCardConverter {

    public Card convert(CardDetail cardDetail) {
        return Card.builder()
                .cardHolderName(cardDetail.getCardHolderName())
                .cardNumber(cardDetail.getCardNumber())
                .expireMonth(cardDetail.getExpireMonth())
                .expireYear(cardDetail.getExpireYear())
                .cvc(cardDetail.getCvc())
                .cardUserKey(cardDetail.getCardUserKey())
                .build();
    }

    public StoreCardRequest convert(CreditCardRequest creditCardRequest) {
        return StoreCardRequest.builder()
                .cardHolderName(creditCardRequest.getCardHolderName())
                .cardNumber(creditCardRequest.getCardNumber())
                .expireMonth(creditCardRequest.getExpireMonth())
                .expireYear(creditCardRequest.getExpireYear())
                .build();
    }

    public CreditCard convertToEntity(StoredCardResponse storedCardResponse, String cardHolderName) {
        return CreditCard.builder()
                .cardHolderName(cardHolderName)
                .cardToken(storedCardResponse.getCardToken())
                .cardUserKey(storedCardResponse.getCardUserKey())
                .cardFamily(storedCardResponse.getCardBrand())
                .status(storedCardResponse.getCardExpiryStatus().name())
                .bankName(storedCardResponse.getCardBankName())
                .cardType(CardType.valueOf(storedCardResponse.getCardType().name()))
                .cardAssociation(CardAssociation.valueOf(storedCardResponse.getCardAssociation().name()))
                .build();
    }

    public DeleteStoredCardRequest convert(DeleteCardRequest deleteCardRequest) {
        return DeleteStoredCardRequest.builder()
                .cardUserKey(deleteCardRequest.getCardUserKey())
                .cardToken(deleteCardRequest.getCardToken())
                .build();
    }
}
