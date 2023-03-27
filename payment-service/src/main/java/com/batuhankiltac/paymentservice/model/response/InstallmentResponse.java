package com.batuhankiltac.paymentservice.model.response;

import com.batuhankiltac.paymentservice.model.InstallmentPrice;
import com.batuhankiltac.paymentservice.model.enums.CardAssociation;
import com.batuhankiltac.paymentservice.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstallmentResponse {
    private String bank;
    private CardType cardType;
    private CardAssociation cardAssociation;
    private String cardBrand;
    private boolean isForce3ds;
    private List<InstallmentPrice> installmentPrices;
}
