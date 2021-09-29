package com.nimi.qe.api.marqeta.response.data;

import java.util.Date;

public class CardsResponse {

    public Date created_time;
    public Date last_modified_time;
    public String token;
    public String user_token;
    public String card_product_token;
    public String last_four;
    public String pan;
    public String expiration;
    public Date expiration_time;
    public String barcode;
    public boolean pin_is_set;
    public String state;
    public String state_reason;
    public String fulfillment_status;
    public String instrument_type;
    public boolean expedite;
    public Metadata metadata;
}
