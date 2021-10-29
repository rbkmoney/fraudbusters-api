package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.proxy_inspector.Context;
import com.rbkmoney.swag.fraudbusters.model.PaymentInspectRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PaymentInspectRequestToContextConverter implements Converter<PaymentInspectRequest, Context> {
    @Override
    public Context convert(PaymentInspectRequest request) {
        return null;
    }
}
