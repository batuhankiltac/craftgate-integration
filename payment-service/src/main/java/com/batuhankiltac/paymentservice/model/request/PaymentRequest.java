package com.batuhankiltac.paymentservice.model.request;

import com.batuhankiltac.paymentservice.model.AddressDetail;
import com.batuhankiltac.paymentservice.model.BuyerDetail;
import com.batuhankiltac.paymentservice.model.CardDetail;
import com.batuhankiltac.paymentservice.model.BasketItem;
import com.batuhankiltac.paymentservice.model.enums.PaymentChannel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    private BigDecimal totalPrice;
    private BigDecimal paidPrice;
    private Integer installmentCount;
    private CardDetail cardDetail;
    private BuyerDetail buyer;
    private AddressDetail shippingAddress;
    private AddressDetail billingAddress;
    private List<BasketItem> basketItems;
    private PaymentChannel paymentChannel;
    private Integer paymentId;
    private Integer installmentNo;
}
