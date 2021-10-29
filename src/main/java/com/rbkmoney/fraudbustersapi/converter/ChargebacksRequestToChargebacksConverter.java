package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.Chargeback;
import com.rbkmoney.swag.fraudbusters.model.ChargebacksRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChargebacksRequestToChargebacksConverter implements Converter<ChargebacksRequest, List<Chargeback>> {
    @Override
    public List<Chargeback> convert(ChargebacksRequest chargebacksRequest) {
        return null;
    }
}
