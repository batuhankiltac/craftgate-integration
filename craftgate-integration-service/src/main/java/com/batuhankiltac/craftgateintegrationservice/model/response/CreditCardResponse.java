package com.batuhankiltac.craftgateintegrationservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardResponse {
    private String cardUserKey;
    private String cardToken;
}
