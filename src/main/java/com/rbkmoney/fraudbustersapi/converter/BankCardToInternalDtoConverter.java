package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.CountryCode;
import com.rbkmoney.damsel.domain.PaymentSystemRef;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BankCardToInternalDtoConverter
        implements Converter<com.rbkmoney.swag.fraudbusters.model.BankCard, BankCard> {

    @Override
    public BankCard convert(com.rbkmoney.swag.fraudbusters.model.BankCard bankCard) {
        return new BankCard()
                .setToken(bankCard.getCardToken())
                .setBin(bankCard.getBin())
                .setLastDigits(bankCard.getLastDigits())
                .setBankName(bankCard.getBankName())
                .setIssuerCountry(CountryCode.valueOf(bankCard.getBinCountryCode()))
                .setPaymentSystem(new PaymentSystemRef()
                        .setId(bankCard.getPaymentSystem())
                );
    }

}
