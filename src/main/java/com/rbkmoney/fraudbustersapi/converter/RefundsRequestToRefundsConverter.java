package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.PayerType;
import com.rbkmoney.damsel.fraudbusters.Refund;
import com.rbkmoney.damsel.fraudbusters.RefundStatus;
import com.rbkmoney.swag.fraudbusters.model.RefundsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RefundsRequestToRefundsConverter implements Converter<RefundsRequest, List<Refund>> {

    private final CacheToInternalDtoConverter cacheToInternalDtoConverter;
    private final ClientInfoToInternalDtoConverter clientInfoToInternalDtoConverter;
    private final PaymentToolToInternalDtoConverter paymentToolToInternalDtoConverter;
    private final ProviderInfoToInternalDtoConverter providerInfoToInternalDtoConverter;
    private final ReferenceInfoToInternalDtoConverter referenceInfoToInternalDtoConverter;
    private final ErrorToInternalDtoConverter errorToInternalDtoConverter;

    @Override
    public List<Refund> convert(RefundsRequest request) {
        return request.getRefunds().stream()
                .map(this::mapRefund)
                .collect(Collectors.toList());
    }

    private Refund mapRefund(com.rbkmoney.swag.fraudbusters.model.Refund item) {
        return new Refund()
                .setCost(cacheToInternalDtoConverter.convert(item.getCashInfo()))
                .setId(item.getId())
                .setEventTime(item.getEventTime().toInstant().toString())
                .setClientInfo(clientInfoToInternalDtoConverter.convert(item.getPayerInfo()))
                .setPayerType(PayerType.valueOf(item.getPayerType().getValue()))
                .setPaymentTool(paymentToolToInternalDtoConverter.convert(item.getBankCard()))
                .setProviderInfo(providerInfoToInternalDtoConverter.convert(item.getProviderInfo()))
                .setReferenceInfo(referenceInfoToInternalDtoConverter.convert(item.getMerchantInfo()))
                .setStatus(RefundStatus.valueOf(item.getStatus().getValue()))
                .setError(errorToInternalDtoConverter.convert(item.getError()))
                .setPaymentId(item.getPaymentId());
    }

}
