package com.rbkmoney.fraudbustersapi.resource;

import com.rbkmoney.damsel.domain.RiskScore;
import com.rbkmoney.damsel.fraudbusters.Chargeback;
import com.rbkmoney.damsel.fraudbusters.FraudPayment;
import com.rbkmoney.damsel.fraudbusters.Refund;
import com.rbkmoney.damsel.fraudbusters.Withdrawal;
import com.rbkmoney.damsel.fraudbusters.*;
import com.rbkmoney.damsel.proxy_inspector.Context;
import com.rbkmoney.damsel.proxy_inspector.InspectorProxySrv;
import com.rbkmoney.swag.fraudbusters.api.FraudbustersApi;
import com.rbkmoney.swag.fraudbusters.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FraudbustersApiImpl implements FraudbustersApi {

    private final PaymentServiceSrv.Iface paymentServiceSrv;
    private final InspectorProxySrv.Iface proxyInspectorSrv;

    private final Converter<ChargebacksRequest, List<Chargeback>> chargebacksRequestToChargebacksConverter;
    private final Converter<FraudPaymentsRequest, List<FraudPayment>> fraudPaymentsRequestToFraudPaymentsConverter;
    private final Converter<PaymentsChangesRequest, List<Payment>> paymentsChangesRequestToPaymentsConverter;
    private final Converter<RefundsRequest, List<Refund>> refundsRequestToRefundsConverter;
    private final Converter<WithdrawalsRequest, List<Withdrawal>> withdrawalsRequestToWithdrawalsConverter;
    private final Converter<PaymentInspectRequest, Context> paymentInspectRequestToContextConverter;

    @Override
    public ResponseEntity<Void> insertChargebacks(ChargebacksRequest chargebacksRequest) {
        log.debug("-> insertChargebacks request: {}", chargebacksRequest);
        try {
            List<Chargeback> chargebacks = chargebacksRequestToChargebacksConverter.convert(chargebacksRequest);
            paymentServiceSrv.insertChargebacks(chargebacks);
        } catch (TException ex) {
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<java.lang.Void> insertFraudPayments(FraudPaymentsRequest fraudPaymentsRequest) {
        log.debug("-> insertFraudPayments request: {}", fraudPaymentsRequest);
        try {
            List<FraudPayment> fraudPayments =
                    fraudPaymentsRequestToFraudPaymentsConverter.convert(fraudPaymentsRequest);
            paymentServiceSrv.insertFraudPayments(fraudPayments);
        } catch (TException ex) {
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<java.lang.Void> insertPaymentsChanges(PaymentsChangesRequest paymentsChangesRequest) {
        log.debug("-> insertPaymentsChanges request: {}", paymentsChangesRequest);
        try {
            List<Payment> payments = paymentsChangesRequestToPaymentsConverter.convert(paymentsChangesRequest);
            paymentServiceSrv.insertPayments(payments);
        } catch (TException ex) {
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<java.lang.Void> insertRefunds(RefundsRequest refundsRequest) {
        log.debug("-> insertRefunds request: {}", refundsRequest);
        try {
            List<Refund> refunds = refundsRequestToRefundsConverter.convert(refundsRequest);
            paymentServiceSrv.insertRefunds(refunds);
        } catch (TException ex) {
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<java.lang.Void> insertWithdrawals(WithdrawalsRequest withdrawalsRequest) {
        log.debug("-> insertWithdrawals request: {}", withdrawalsRequest);
        try {
            paymentServiceSrv.insertWithdrawals(withdrawalsRequestToWithdrawalsConverter.convert(withdrawalsRequest));
        } catch (TException ex) {
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<RiskScoreResult> inspectPayment(PaymentInspectRequest paymentInspectRequest) {
        log.debug("-> inspectPayment request: {}", paymentInspectRequest);
        try {
            Context context = paymentInspectRequestToContextConverter.convert(paymentInspectRequest);
            RiskScore riskScore = proxyInspectorSrv.inspectPayment(context);
            RiskScoreResult riskScoreResult = new RiskScoreResult();
            riskScoreResult.setResult(com.rbkmoney.swag.fraudbusters.model.RiskScore.fromValue(riskScore.name()));
            log.debug("<- inspectPayment riskScoreResult: {}", riskScoreResult);
            return ResponseEntity.ok(riskScoreResult);
        } catch (TException ex) {
            throw new RuntimeException(ex);
        }
    }
}
