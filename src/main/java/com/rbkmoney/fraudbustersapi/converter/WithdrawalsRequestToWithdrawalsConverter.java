package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.Withdrawal;
import com.rbkmoney.swag.fraudbusters.model.WithdrawalsRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WithdrawalsRequestToWithdrawalsConverter implements Converter<WithdrawalsRequest, List<Withdrawal>> {
    @Override
    public List<Withdrawal> convert(WithdrawalsRequest request) {
        return null;
    }
}
