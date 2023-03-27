package com.batuhankiltac.craftgateintegrationservice.helper;

import com.batuhankiltac.craftgateintegrationservice.exception.CraftgatePaymentException;
import com.batuhankiltac.craftgateintegrationservice.exception.CraftgateRefundException;
import io.craftgate.model.PaymentStatus;
import io.craftgate.model.RefundStatus;
import io.craftgate.response.PaymentRefundResponse;
import io.craftgate.response.PaymentResponse;
import io.craftgate.response.PaymentTransactionRefundResponse;
import org.springframework.http.HttpStatus;

public class CraftgateValidator {

    public static void assertIfCraftgatePaymentStatusIsSuccess(PaymentResponse paymentResponse) {
        if (PaymentStatus.FAILURE.equals(paymentResponse.getPaymentStatus())) {
            throw new CraftgatePaymentException(paymentResponse.getPaymentError().getErrorDescription(),
                                                paymentResponse.getConversationId(),
                                                HttpStatus.BAD_REQUEST);
        }
    }

    public static void assertIfCraftgatePaymentRefundStatusIsSuccess(PaymentRefundResponse paymentRefundResponse) {
        if (RefundStatus.FAILURE.equals(paymentRefundResponse.getStatus())) {
            throw new CraftgateRefundException("refund.exception.message",
                                               paymentRefundResponse.getConversationId(),
                                               HttpStatus.BAD_REQUEST);
        }
    }

    public static void assertIfCraftgatePaymentRefundTransactionStatusIsSuccess(PaymentTransactionRefundResponse paymentTransactionRefundResponse) {
        if (RefundStatus.FAILURE.equals(paymentTransactionRefundResponse.getStatus())) {
            throw new CraftgateRefundException("refund.exception.message",
                                                paymentTransactionRefundResponse.getConversationId(),
                                                HttpStatus.BAD_REQUEST);
        }
    }
}
