package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.FraudPayment;
import com.rbkmoney.swag.fraudbusters.model.FraudPaymentsRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FraudPaymentsRequestToFraudPaymentsConverter
        implements Converter<FraudPaymentsRequest, List<FraudPayment>> {

    @Override
    public List<FraudPayment> convert(FraudPaymentsRequest request) {
        return request.getFraudPayments().stream()
                .map(this::mapFraudPayment)
                .collect(Collectors.toList());
    }

    private FraudPayment mapFraudPayment(com.rbkmoney.swag.fraudbusters.model.FraudPayment item) {
        return new FraudPayment()
                .setId(item.getPayemntId())
                .setEventTime(item.getEventTime().toInstant().toString())
                .setComment(item.getComment())
                .setType(item.getType());
    }
}
