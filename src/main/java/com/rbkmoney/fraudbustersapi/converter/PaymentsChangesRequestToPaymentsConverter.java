package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.Error;
import com.rbkmoney.damsel.fraudbusters.PayerType;
import com.rbkmoney.damsel.fraudbusters.Payment;
import com.rbkmoney.damsel.fraudbusters.PaymentStatus;
import com.rbkmoney.swag.fraudbusters.model.PaymentContext;
import com.rbkmoney.swag.fraudbusters.model.PaymentsChangesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PaymentsChangesRequestToPaymentsConverter implements Converter<PaymentsChangesRequest, List<Payment>> {

    private final CacheToInternalDtoConverter cacheToInternalDtoConverter;
    private final ClientInfoToInternalDtoConverter clientInfoToInternalDtoConverter;
    private final PaymentToolToInternalDtoConverter paymentToolToInternalDtoConverter;
    private final ProviderInfoToInternalDtoConverter providerInfoToInternalDtoConverter;
    private final ReferenceInfoToInternalDtoConverter referenceInfoToInternalDtoConverter;
    private final ErrorToInternalDtoConverter errorToInternalDtoConverter;

    @Override
    public List<Payment> convert(PaymentsChangesRequest request) {
        return request.getPaymentsChanges().stream()
                .map(this::mapPayment)
                .collect(Collectors.toList());
    }

    private Payment mapPayment(com.rbkmoney.swag.fraudbusters.model.PaymentChange item) {
        PaymentContext paymentContext = item.getPaymentContext();
        return new Payment()
                .setCost(cacheToInternalDtoConverter.convert(paymentContext.getCashInfo()))
                .setId(paymentContext.getId())
                .setEventTime(paymentContext.getCreatedAt().toInstant().toString())
                .setClientInfo(clientInfoToInternalDtoConverter.convert(paymentContext.getPayerInfo()))
                .setPayerType(PayerType.valueOf(paymentContext.getPayerType().getValue()))
                .setPaymentTool(paymentToolToInternalDtoConverter.convert(paymentContext.getBankCard()))
                .setProviderInfo(providerInfoToInternalDtoConverter.convert(paymentContext.getProviderInfo()))
                .setReferenceInfo(referenceInfoToInternalDtoConverter.convert(paymentContext.getMerchantInfo()))
                .setStatus(PaymentStatus.valueOf(item.getPaymentStatus().getValue()))
                .setRecurrent(paymentContext.getRecurrent())
                .setMobile(paymentContext.getMobile())
                .setError(errorToInternalDtoConverter.convert(item.getError()));
    }

}
