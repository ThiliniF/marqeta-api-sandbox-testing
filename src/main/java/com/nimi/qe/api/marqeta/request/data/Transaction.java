package com.nimi.qe.api.marqeta.request.data;

import lombok.Data;

@Data
public class Transaction {

    private double amount;
    private String mid;
    private String card_token;

}
