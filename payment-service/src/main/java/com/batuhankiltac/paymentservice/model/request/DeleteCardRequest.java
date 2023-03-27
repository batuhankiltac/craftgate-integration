package com.batuhankiltac.paymentservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteCardRequest {
    private Integer id;
    private String cardUserKey;
    private String cardToken;
}
