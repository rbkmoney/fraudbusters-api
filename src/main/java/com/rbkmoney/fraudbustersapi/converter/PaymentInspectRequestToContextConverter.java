package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.domain.*;
import com.rbkmoney.damsel.proxy_inspector.Invoice;
import com.rbkmoney.damsel.proxy_inspector.InvoicePayment;
import com.rbkmoney.damsel.proxy_inspector.Party;
import com.rbkmoney.damsel.proxy_inspector.Shop;
import com.rbkmoney.damsel.proxy_inspector.*;
import com.rbkmoney.swag.fraudbusters.model.MerchantInfo;
import com.rbkmoney.swag.fraudbusters.model.PaymentContext;
import com.rbkmoney.swag.fraudbusters.model.PaymentInspectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentInspectRequestToContextConverter implements Converter<PaymentInspectRequest, Context> {

    public static final String MOCK_UNUSED_DATA = "MOCK_UNUSED_DATA";
    private final CacheToInternalDtoConverter cacheToInternalDtoConverter;

    @Override
    public Context convert(PaymentInspectRequest request) {
        PaymentContext context = request.getContext();
        com.rbkmoney.swag.fraudbusters.model.BankCard bankCard = context.getBankCard();
        MerchantInfo merchantInfo = context.getMerchantInfo();
        return new Context()
                .setPayment(new PaymentInfo()
                        .setShop(initShop(merchantInfo))
                        .setParty(initParty(merchantInfo))
                        .setInvoice(initInvoice(request, context))
                        .setPayment(initInvoicePayment(context, bankCard))
                );
    }

    private InvoicePayment initInvoicePayment(PaymentContext context,
                                              com.rbkmoney.swag.fraudbusters.model.BankCard bankCard) {
        return new InvoicePayment()
                .setCost(cacheToInternalDtoConverter.convert(context.getCashInfo()))
                .setId(context.getId())
                .setCreatedAt(context.getCreatedAt().toInstant().toString())
                .setPayer(Payer.payment_resource(new PaymentResourcePayer()
                                .setContactInfo(new ContactInfo()
                                        .setEmail(context.getPayerInfo().getEmail())
                                        .setPhoneNumber(context.getPayerInfo().getPhone())
                                )
                                .setResource(new DisposablePaymentResource()
                                        .setPaymentTool(PaymentTool.bank_card(new BankCard()
                                                .setPaymentSystem(new PaymentSystemRef()
                                                        .setId(bankCard.getPaymentSystem()))
                                                .setIssuerCountry(CountryCode.valueOf(
                                                        bankCard.getBinCountryCode()))
                                                .setBankName(bankCard.getBankName())
                                                .setBin(bankCard.getBin())
                                                .setLastDigits(bankCard.getLastDigits())
                                                .setToken(bankCard.getCardToken())
                                        ))
                                )
                        )
                )
                .setMakeRecurrent(context.getRecurrent());
    }

    private Invoice initInvoice(PaymentInspectRequest request, PaymentContext context) {
        return new Invoice()
                .setId(request.getContext().getId())
                .setDetails(new InvoiceDetails()
                        // TODO add product to swag
                        .setProduct(MOCK_UNUSED_DATA))
                .setCreatedAt(context.getCreatedAt().toInstant().toString())
                .setDue(context.getCreatedAt().toInstant().toString());
    }

    private Party initParty(MerchantInfo merchantInfo) {
        return new Party()
                .setPartyId(merchantInfo.getPartyId());
    }

    private Shop initShop(MerchantInfo merchantInfo) {
        return new Shop()
                .setId(merchantInfo.getShopId())
                .setDetails(new ShopDetails()
                        .setName(MOCK_UNUSED_DATA)
                )
                .setCategory(new Category()
                        .setName(MOCK_UNUSED_DATA)
                        .setDescription(MOCK_UNUSED_DATA))
                .setLocation(ShopLocation.url(MOCK_UNUSED_DATA));
    }
}
