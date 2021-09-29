package com.nimi.qe.api.marqeta.response.data;

public class TransactionControls {
    public String accepted_countries_token;
    public boolean always_require_pin;
    public boolean always_require_icc;
    public boolean allow_gpa_auth;
    public boolean require_card_not_present_card_security_code;
    public boolean allow_mcc_group_authorization_controls;
    public boolean allow_first_pin_set_via_financial_transaction;
    public boolean ignore_card_suspended_state;
    public boolean allow_chip_fallback;
    public boolean allow_network_load;
    public boolean allow_network_load_card_activation;
    public boolean allow_quasi_cash;
    public boolean enable_partial_auth_approval;
    public AddressVerification address_verification;
    public StrongCustomerAuthenticationLimits strong_customer_authentication_limits;
    public String quasi_cash_exempt_mids;
}
