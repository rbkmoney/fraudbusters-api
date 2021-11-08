package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.domain.Cash;
import com.rbkmoney.damsel.domain.CurrencyRef;
import com.rbkmoney.swag.fraudbusters.model.CashInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CacheToInternalDtoConverter
        implements Converter<com.rbkmoney.swag.fraudbusters.model.CashInfo, Cash> {

    @Override
    public Cash convert(CashInfo cashInfo) {
        return new Cash()
                .setAmount(cashInfo.getAmount())
                .setCurrency(new CurrencyRef()
                        .setSymbolicCode(cashInfo.getCurrency()));
    }

}
