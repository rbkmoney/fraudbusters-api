package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.domain.CurrencyRef;
import com.rbkmoney.damsel.fraudbusters.Account;
import com.rbkmoney.damsel.fraudbusters.Resource;
import com.rbkmoney.damsel.fraudbusters.Withdrawal;
import com.rbkmoney.damsel.fraudbusters.WithdrawalStatus;
import com.rbkmoney.swag.fraudbusters.model.WithdrawalsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WithdrawalsRequestToWithdrawalsConverter implements Converter<WithdrawalsRequest, List<Withdrawal>> {

    public static final String UNKNOWN = "UNKNOWN";
    private final CacheToInternalDtoConverter cacheToInternalDtoConverter;
    private final ErrorToInternalDtoConverter errorToInternalDtoConverter;
    private final ProviderInfoToInternalDtoConverter providerInfoToInternalDtoConverter;
    private final BankCardToInternalDtoConverter bankCardToInternalDtoConverter;

    @Override
    public List<Withdrawal> convert(WithdrawalsRequest request) {
        return request.getWithdrawals().stream()
                .map(this::mapWithdrawal)
                .collect(Collectors.toList());
    }

    private Withdrawal mapWithdrawal(com.rbkmoney.swag.fraudbusters.model.Withdrawal item) {
        return new Withdrawal()
                .setCost(cacheToInternalDtoConverter.convert(item.getCashInfo()))
                .setId(item.getId())
                .setEventTime(item.getEventTime().toInstant().toString())
                .setProviderInfo(providerInfoToInternalDtoConverter.convert(item.getProviderInfo()))
                .setStatus(WithdrawalStatus.valueOf(item.getStatus().getValue()))
                .setError(errorToInternalDtoConverter.convert(item.getError()))
                .setAccount(new Account()
                        .setId(item.getAccount().getAccountId())
                        .setIdentity(UNKNOWN)
                        .setCurrency(new CurrencyRef(item.getAccount().getCurrency())))
                .setDestinationResource(Resource.bank_card(bankCardToInternalDtoConverter.convert(item.getBankCard())));
    }

}
