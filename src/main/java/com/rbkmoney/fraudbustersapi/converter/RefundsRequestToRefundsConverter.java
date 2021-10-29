package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.Refund;
import com.rbkmoney.swag.fraudbusters.model.RefundsRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RefundsRequestToRefundsConverter implements Converter<RefundsRequest, List<Refund>> {
    @Override
    public List<Refund> convert(RefundsRequest request) {
        return null;
    }
}
