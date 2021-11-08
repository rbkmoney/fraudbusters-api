package com.rbkmoney.fraudbustersapi.utils;

import com.rbkmoney.swag.fraudbusters.model.*;

import java.time.OffsetDateTime;

public class ApiBeanGenerator {

    public static final String TEST = "test";
    public static final String RUS = "RUS";
    public static final String TERMINAL_ID = "12321321313";
    public static final String BIN = "4242424242424242";
    public static final String LAST_DIGITS = "1234";
    public static final String VISA = "visa";
    public static final String IP = "192.168.0.1";
    public static final String PHONE = "+79651112233";
    public static final String ACCOUNT_ID = "123123123123";

    public static Chargeback initChargeback() {
        return new Chargeback().chargebackCode(TEST)
                .eventTime(OffsetDateTime.now())
                .id(TEST)
                .status(ChargebackStatus.ACCEPTED)
                .payerType(PayerType.CUSTOMER)
                .category(ChargebackCategory.FRAUD)
                .merchantInfo(initMerachantInfo())
                .chargebackCode(TEST)
                .paymentId(TEST)
                .providerInfo(initProviderInfo())
                .bankCard(initBankCard())
                .cashInfo(initCashInfo())
                .payerInfo(initUserInfo());
    }

    public static Refund initRefund() {
        return new Refund()
                .eventTime(OffsetDateTime.now())
                .id(TEST)
                .status(RefundStatus.SUCCEEDED)
                .payerType(PayerType.CUSTOMER)
                .merchantInfo(initMerachantInfo())
                .paymentId(TEST)
                .providerInfo(initProviderInfo())
                .bankCard(initBankCard())
                .cashInfo(initCashInfo())
                .payerInfo(initUserInfo());
    }

    public static Withdrawal initWithdrawal() {
        return new Withdrawal()
                .eventTime(OffsetDateTime.now())
                .id(TEST)
                .status(WithdrawalStatus.SUCCEEDED)
                .providerInfo(initProviderInfo())
                .bankCard(initBankCard())
                .cashInfo(initCashInfo())
                .accountId(ACCOUNT_ID);
    }

    public static PaymentChange initPaymentChange() {
        return new PaymentChange()
                .eventTime(OffsetDateTime.now())
                .paymentStatus(PaymentStatus.CAPTURED)
                .paymentContext(initPaymentContext()
                );
    }

    public static PaymentContext initPaymentContext() {
        return new PaymentContext()
                .id(TEST)
                .payerType(PayerType.CUSTOMER)
                .merchantInfo(initMerachantInfo())
                .id(TEST)
                .providerInfo(initProviderInfo())
                .bankCard(initBankCard())
                .cashInfo(initCashInfo())
                .payerInfo(initUserInfo())
                .createdAt(OffsetDateTime.now());
    }

    public static FraudPayment initFraudPayment() {
        return new FraudPayment()
                .eventTime(OffsetDateTime.now())
                .payemntId(TEST)
                .comment(TEST)
                .type(TEST);
    }

    public static UserInfo initUserInfo() {
        return new UserInfo()
                .email(TEST)
                .fingerprint(TEST)
                .ip(IP)
                .phone(PHONE);
    }

    public static CashInfo initCashInfo() {
        return new CashInfo()
                .amount(1000L)
                .currency("RUB");
    }

    public static BankCard initBankCard() {
        return new BankCard()
                .bankName(TEST)
                .cardToken(TEST)
                .cardType(CardType.CREDIT)
                .bin(BIN)
                .binCountryCode(RUS)
                .lastDigits(LAST_DIGITS)
                .paymentSystem(VISA);
    }

    public static ProviderInfo initProviderInfo() {
        return new ProviderInfo()
                .providerId(TEST)
                .country(RUS)
                .terminalId(TERMINAL_ID);
    }

    public static MerchantInfo initMerachantInfo() {
        return new MerchantInfo()
                .partyId(TEST)
                .shopId(TEST);
    }

}
