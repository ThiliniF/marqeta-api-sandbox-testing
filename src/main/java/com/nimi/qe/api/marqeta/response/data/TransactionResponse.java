package com.nimi.qe.api.marqeta.response.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionResponse {
    public Transaction transaction;
    @JsonProperty("raw_iso8583")
    public Object raw_iso8583;
}
