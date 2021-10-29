package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.Payment;
import com.rbkmoney.swag.fraudbusters.model.PaymentsChangesRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentsChangesRequestToPaymentsConverter implements Converter<PaymentsChangesRequest, List<Payment>> {
    @Override
    public List<Payment> convert(PaymentsChangesRequest request) {
        return null;
    }
}
