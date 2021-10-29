package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.FraudPayment;
import com.rbkmoney.swag.fraudbusters.model.FraudPaymentsRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FraudPaymentsRequestToFraudPaymentsConverter
        implements Converter<FraudPaymentsRequest, List<FraudPayment>> {
    @Override
    public List<FraudPayment> convert(FraudPaymentsRequest fraudPaymentsRequest) {
        return null;
    }
}
