package com.batuhankiltac.craftgateintegrationservice.model.request;

import com.batuhankiltac.craftgateintegrationservice.model.AddressDetail;
import com.batuhankiltac.craftgateintegrationservice.model.BasketItem;
import com.batuhankiltac.craftgateintegrationservice.model.BuyerDetail;
import com.batuhankiltac.craftgateintegrationservice.model.CardDetail;
import com.batuhankiltac.craftgateintegrationservice.model.enums.PaymentChannel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    @NotNull(message = "paymentRequest.totalPrice.notNull")
    @Positive(message = "paymentRequest.totalPrice.couldBePositive")
    private BigDecimal totalPrice;

    @NotNull(message = "paymentRequest.paidPrice.notNull")
    @Positive(message = "paymentRequest.paidPrice.couldBePositive")
    private BigDecimal paidPrice;

    @NotNull(message = "paymentRequest.installmentCount.notNull")
    @Min(value = 1, message = "paymentRequest.installmentCount.couldBeMin")
    private Integer installmentCount;

    @NotNull(message = "paymentRequest.installmentNo.notNull")
    @Min(value = 1, message = "paymentRequest.installmentNo.couldBeMin")
    private Integer installmentNo;

    @Valid
    @NotNull(message = "paymentRequest.cardDetail.notNull")
    private CardDetail cardDetail;

    @Valid
    @NotNull(message = "paymentRequest.buyerDetail.notNull")
    private BuyerDetail buyer;

    @Valid
    @NotNull(message = "paymentRequest.addressDetail.notNull")
    private AddressDetail billingAddress;
    private AddressDetail shippingAddress;

    @Valid
    @NotEmpty(message = "paymentRequest.basketItems.notEmpty")
    private List<BasketItem> basketItems;

    @NotNull(message = "paymentRequest.paymentChannel.notNull")
    private PaymentChannel paymentChannel;
    private Integer paymentId;
}
