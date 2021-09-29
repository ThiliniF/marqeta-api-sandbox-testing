package com.nimi.qe.api.marqeta.response.data;

import java.util.Date;

public class GpaOrder {

    public String token;
    public double amount;
    public Date created_time;
    public Date last_modified_time;
    public String transaction_token;
    public String state;
    public Response response;
    public Funding funding;
    public String funding_source_token;
    public String user_token;
    public String currency_code;
}
