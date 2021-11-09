package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.Withdrawal;
import com.rbkmoney.damsel.fraudbusters.WithdrawalStatus;
import com.rbkmoney.fraudbustersapi.utils.ApiBeanGenerator;
import com.rbkmoney.swag.fraudbusters.model.WithdrawalsRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WithdrawalsRequestToWithdrawalsConverterTest {

    @Mock
    CacheToInternalDtoConverter cacheToInternalDtoConverter;
    @Mock
    ProviderInfoToInternalDtoConverter providerInfoToInternalDtoConverter;
    @Mock
    ErrorToInternalDtoConverter errorToInternalDtoConverter;

    @Test
    void convert() {
        WithdrawalsRequestToWithdrawalsConverter converter = new WithdrawalsRequestToWithdrawalsConverter(
                cacheToInternalDtoConverter,
                errorToInternalDtoConverter,
                providerInfoToInternalDtoConverter,
                new BankCardToInternalDtoConverter()
        );

        List<Withdrawal> withdrawals = converter.convert(new WithdrawalsRequest().withdrawals(
                List.of(ApiBeanGenerator.initWithdrawal())));

        assertNotNull(withdrawals);
        assertFalse(withdrawals.isEmpty());

        Withdrawal withdrawal = withdrawals.get(0);
        assertNotNull(withdrawal);
        assertEquals(ApiBeanGenerator.ID, withdrawal.getId());
        assertNotNull(withdrawal.getEventTime());
        assertEquals(WithdrawalStatus.succeeded, withdrawal.getStatus());
        assertEquals(ApiBeanGenerator.ACCOUNT_ID, withdrawal.getAccount().getId());
    }
}
