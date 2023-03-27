package com.batuhankiltac.craftgateintegrationservice.model.response;

import com.batuhankiltac.craftgateintegrationservice.model.BasketItem;
import com.batuhankiltac.craftgateintegrationservice.model.enums.CardAssociation;
import com.batuhankiltac.craftgateintegrationservice.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private LocalDateTime createdDate;
    private String conversationId;
    private String providerPaymentId;
    private String binNumber;
    private CardAssociation cardAssociation;
    private String cardFamily;
    private CardType cardType;
    private List<BasketItem> basketItems;
    private String status;
    private String errorMessage;
    private String errorGroup;
}
