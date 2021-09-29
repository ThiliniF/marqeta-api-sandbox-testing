package com.nimi.qe.api.marqeta.response.data;

import lombok.Data;

@Data
public class DepositAccount {

    public String token;
    public String account_number;
    public String routing_number;
    public boolean allow_immediate_credit;
}
