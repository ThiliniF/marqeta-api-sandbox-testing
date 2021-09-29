package com.nimi.qe.api.marqeta.response.data;

import java.util.Date;

public class Transaction {

    public String type;
    public String state;
    public String identifier;
    public String token;
    public String user_token;
    public String acting_user_token;
    public String card_token;
    public Gpa gpa;
    public GpaOrder gpa_order;
    public int duration;
    public Date created_time;
    public Date user_transaction_time;
    public Date settlement_date;
    public double request_amount;
    public double amount;
    public int issuer_interchange_amount;
    public String currency_code;
    public String approval_code;
    public Response response;
    public String network;
    public int acquirer_fee_amount;
    public Acquirer acquirer;
    public User user;
    public Card card;
    public Date issuer_received_time;
    public String issuer_payment_node;
    public String network_reference_id;
    public CardAcceptor card_acceptor;
    public boolean is_recurring;
    public boolean is_installment;
}
