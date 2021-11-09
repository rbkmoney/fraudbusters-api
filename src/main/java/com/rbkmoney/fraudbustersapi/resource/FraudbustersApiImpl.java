package com.rbkmoney.fraudbustersapi.resource;

import com.rbkmoney.damsel.fraudbusters.Chargeback;
import com.rbkmoney.damsel.fraudbusters.FraudPayment;
import com.rbkmoney.damsel.fraudbusters.Refund;
import com.rbkmoney.damsel.fraudbusters.Withdrawal;
import com.rbkmoney.damsel.fraudbusters.*;
import com.rbkmoney.damsel.proxy_inspector.Context;
import com.rbkmoney.fraudbustersapi.service.FraudbustersDataService;
import com.rbkmoney.fraudbustersapi.service.FraudbustersInspectorService;
import com.rbkmoney.swag.fraudbusters.api.FraudbustersApi;
import com.rbkmoney.swag.fraudbusters.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FraudbustersApiImpl implements FraudbustersApi {

    private final FraudbustersDataService fraudbustersService;
    private final FraudbustersInspectorService fraudbustersInspectorService;

    private final Converter<ChargebacksRequest, List<Chargeback>> chargebacksRequestToChargebacksConverter;
    private final Converter<FraudPaymentsRequest, List<FraudPayment>> fraudPaymentsRequestToFraudPaymentsConverter;
    private final Converter<PaymentsChangesRequest, List<Payment>> paymentsChangesRequestToPaymentsConverter;
    private final Converter<RefundsRequest, List<Refund>> refundsRequestToRefundsConverter;
    private final Converter<WithdrawalsRequest, List<Withdrawal>> withdrawalsRequestToWithdrawalsConverter;
    private final Converter<PaymentInspectRequest, Context> paymentInspectRequestToContextConverter;

    @Override
    public ResponseEntity<Void> insertChargebacks(ChargebacksRequest chargebacksRequest) {
        log.debug("-> insertChargebacks request: {}", chargebacksRequest);
        if (!CollectionUtils.isEmpty(chargebacksRequest.getChargebacks())) {
            List<Chargeback> chargebacks = chargebacksRequestToChargebacksConverter.convert(chargebacksRequest);
            fraudbustersService.insertChargebacks(chargebacks);
        }
        log.debug("<- insertChargebacks success request: {}", chargebacksRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> insertFraudPayments(FraudPaymentsRequest fraudPaymentsRequest) {
        log.debug("-> insertFraudPayments request: {}", fraudPaymentsRequest);
        if (!CollectionUtils.isEmpty(fraudPaymentsRequest.getFraudPayments())) {
            List<FraudPayment> fraudPayments =
                    fraudPaymentsRequestToFraudPaymentsConverter.convert(fraudPaymentsRequest);
            fraudbustersService.insertFraudPayments(fraudPayments);
        }
        log.debug("<- insertFraudPayments success request: {}", fraudPaymentsRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> insertPaymentsChanges(PaymentsChangesRequest paymentsChangesRequest) {
        log.debug("-> insertPaymentsChanges request: {}", paymentsChangesRequest);
        if (!CollectionUtils.isEmpty(paymentsChangesRequest.getPaymentsChanges())) {
            List<Payment> payments = paymentsChangesRequestToPaymentsConverter.convert(paymentsChangesRequest);
            fraudbustersService.insertPaymentsChanges(payments);
        }
        log.debug("<- insertPaymentsChanges success request: {}", paymentsChangesRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> insertRefunds(RefundsRequest refundsRequest) {
        log.debug("-> insertRefunds request: {}", refundsRequest);
        if (!CollectionUtils.isEmpty(refundsRequest.getRefunds())) {
            List<Refund> refunds = refundsRequestToRefundsConverter.convert(refundsRequest);
            fraudbustersService.insertRefunds(refunds);
        }
        log.debug("<- insertRefunds success request: {}", refundsRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> insertWithdrawals(WithdrawalsRequest withdrawalsRequest) {
        log.debug("-> insertWithdrawals request: {}", withdrawalsRequest);
        if (!CollectionUtils.isEmpty(withdrawalsRequest.getWithdrawals())) {
            List<Withdrawal> withdrawals = withdrawalsRequestToWithdrawalsConverter.convert(withdrawalsRequest);
            fraudbustersService.insertWithdrawals(withdrawals);
        }
        log.debug("<- insertWithdrawals success request: {}", withdrawalsRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<RiskScoreResult> inspectPayment(PaymentInspectRequest paymentInspectRequest) {
        log.debug("-> inspectPayment request: {}", paymentInspectRequest);
        Context context = paymentInspectRequestToContextConverter.convert(paymentInspectRequest);
        RiskScore riskScore = fraudbustersInspectorService.inspectPayment(context);
        RiskScoreResult riskScoreResult = new RiskScoreResult();
        riskScoreResult.setResult(riskScore);
        log.debug("<- inspectPayment riskScoreResult: {}", riskScoreResult);
        return ResponseEntity.ok(riskScoreResult);
    }
}
