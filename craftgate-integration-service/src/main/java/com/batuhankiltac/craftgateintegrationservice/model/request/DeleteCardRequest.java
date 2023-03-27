package com.batuhankiltac.craftgateintegrationservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteCardRequest {
    @NotNull(message = "deleteCardRequest.id.notNull")
    private Integer id;

    @NotNull(message = "deleteCardRequest.cardUserKey.notNull")
    private String cardUserKey;

    @NotNull(message = "deleteCardRequest.cardToken.notNull")
    private String cardToken;
}
