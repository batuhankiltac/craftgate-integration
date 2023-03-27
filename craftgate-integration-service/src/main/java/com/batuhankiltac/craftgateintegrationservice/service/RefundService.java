package com.batuhankiltac.craftgateintegrationservice.service;

import com.batuhankiltac.craftgateintegrationservice.configuration.craftgate.CraftgateConfiguration;
import com.batuhankiltac.craftgateintegrationservice.converter.refund.RefundConverter;
import com.batuhankiltac.craftgateintegrationservice.helper.CraftgateValidator;
import com.batuhankiltac.craftgateintegrationservice.model.request.RefundRequest;
import io.craftgate.adapter.PaymentAdapter;
import io.craftgate.request.RefundPaymentRequest;
import io.craftgate.request.RefundPaymentTransactionRequest;
import io.craftgate.response.PaymentRefundResponse;
import io.craftgate.response.PaymentTransactionRefundResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefundService {
    private final RefundConverter refundConverter;
    private final PaymentAdapter paymentAdapter = new PaymentAdapter(CraftgateConfiguration.getOptions());

    public void refund(RefundRequest refundRequest) {
        RefundPaymentRequest refundPaymentRequest = refundConverter.convert(refundRequest);
        log.info("refundPaymentRequest: {}", refundPaymentRequest);

        PaymentRefundResponse paymentRefundResponse = paymentAdapter.refundPayment(refundPaymentRequest);
        log.info("paymentRefundResponse: {}", paymentRefundResponse);

        CraftgateValidator.assertIfCraftgatePaymentRefundStatusIsSuccess(paymentRefundResponse);
    }

    public void refundTransaction(RefundRequest refundRequest) {
        RefundPaymentTransactionRequest refundPaymentTransactionRequest = refundConverter.convertTo(refundRequest);
        log.info("refundPaymentTransactionRequest: {}", refundPaymentTransactionRequest);

        PaymentTransactionRefundResponse paymentTransactionRefundResponse = paymentAdapter.refundPaymentTransaction(refundPaymentTransactionRequest);
        log.info("paymentTransactionRefundResponse: {}", paymentTransactionRefundResponse);

        CraftgateValidator.assertIfCraftgatePaymentRefundTransactionStatusIsSuccess(paymentTransactionRefundResponse);
    }
}
