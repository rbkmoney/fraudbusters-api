package com.rbkmoney.fraudbustersapi.utils;

import com.rbkmoney.swag.fraudbusters.model.Error;
import com.rbkmoney.swag.fraudbusters.model.*;

import java.time.OffsetDateTime;

public class ApiBeanGenerator {

    public static final String RUS = "RUS";
    public static final String TERMINAL_ID = "12321321313";
    public static final String BIN = "4242424242424242";
    public static final String LAST_DIGITS = "1234";
    public static final String VISA = "visa";
    public static final String IP = "192.168.0.1";
    public static final String PHONE = "+79651112233";
    public static final String ACCOUNT_ID = "123123123123";
    public static final String CHARGE_CODE = "CHARGE_CODE";
    public static final String ID = "ID";
    public static final String PAYMENT_ID = "PAYMENT_ID";
    public static final String COMMENT = "COMMENT";
    public static final String TYPE = "TYPE";
    public static final String EMAIL = "EMAIL";
    public static final String FINGERPRONT = "FINGERPRONT";
    public static final long AMOUNT = 1000L;
    public static final String CURRENCY = "RUB";
    public static final String BANK_NAME = "BANK_NAME";
    public static final String CARD_TOKEN = "CARD_TOKEN";
    public static final String PROVIDER_ID = "PROVIDER_ID";
    public static final String PARTY_ID = "PARTY_ID";
    public static final String SHOP_ID = "SHOP_ID";
    public static final String ERROR_CODE = "ERROR_CODE";
    public static final String ERROR_REASON = "ERROR_REASON";
    public static final boolean MOBILE = true;
    public static final boolean RECURRENT = false;

    public static Chargeback initChargeback() {
        return new Chargeback()
                .eventTime(OffsetDateTime.now())
                .id(ID)
                .status(ChargebackStatus.ACCEPTED)
                .payerType(PayerType.CUSTOMER)
                .category(ChargebackCategory.FRAUD)
                .merchantInfo(initMerachantInfo())
                .chargebackCode(CHARGE_CODE)
                .paymentId(PAYMENT_ID)
                .providerInfo(initProviderInfo())
                .bankCard(initBankCard())
                .cashInfo(initCashInfo())
                .payerInfo(initUserInfo());
    }

    public static Refund initRefund() {
        return new Refund()
                .eventTime(OffsetDateTime.now())
                .id(ID)
                .status(RefundStatus.SUCCEEDED)
                .payerType(PayerType.CUSTOMER)
                .merchantInfo(initMerachantInfo())
                .paymentId(PAYMENT_ID)
                .providerInfo(initProviderInfo())
                .bankCard(initBankCard())
                .cashInfo(initCashInfo())
                .payerInfo(initUserInfo());
    }

    public static Withdrawal initWithdrawal() {
        return new Withdrawal()
                .eventTime(OffsetDateTime.now())
                .id(ID)
                .status(WithdrawalStatus.SUCCEEDED)
                .providerInfo(initProviderInfo())
                .bankCard(initBankCard())
                .cashInfo(initCashInfo())
                .account(new Account()
                        .accountId(ACCOUNT_ID)
                        .currency("RUB"));
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
                .id(ID)
                .payerType(PayerType.CUSTOMER)
                .merchantInfo(initMerachantInfo())
                .providerInfo(initProviderInfo())
                .bankCard(initBankCard())
                .cashInfo(initCashInfo())
                .payerInfo(initUserInfo())
                .createdAt(OffsetDateTime.now())
                .mobile(MOBILE)
                .recurrent(RECURRENT);
    }

    public static FraudPayment initFraudPayment() {
        return new FraudPayment()
                .eventTime(OffsetDateTime.now())
                .payemntId(PAYMENT_ID)
                .comment(COMMENT)
                .type(TYPE);
    }

    public static UserInfo initUserInfo() {
        return new UserInfo()
                .email(EMAIL)
                .fingerprint(FINGERPRONT)
                .ip(IP)
                .phone(PHONE);
    }

    public static CashInfo initCashInfo() {
        return new CashInfo()
                .amount(AMOUNT)
                .currency(CURRENCY);
    }

    public static BankCard initBankCard() {
        return new BankCard()
                .bankName(BANK_NAME)
                .cardToken(CARD_TOKEN)
                .cardType(CardType.CREDIT)
                .bin(BIN)
                .binCountryCode(RUS)
                .lastDigits(LAST_DIGITS)
                .paymentSystem(VISA);
    }

    public static ProviderInfo initProviderInfo() {
        return new ProviderInfo()
                .providerId(PROVIDER_ID)
                .country(RUS)
                .terminalId(TERMINAL_ID);
    }

    public static MerchantInfo initMerachantInfo() {
        return new MerchantInfo()
                .partyId(PARTY_ID)
                .shopId(SHOP_ID);
    }

    public static Error initError() {
        return new Error()
                .errorCode(ERROR_CODE)
                .errorMessage(ERROR_REASON);
    }

}
