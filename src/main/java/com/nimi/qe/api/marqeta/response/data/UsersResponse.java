package com.nimi.qe.api.marqeta.response.data;

import lombok.Data;

import java.util.Date;

@Data
public class UsersResponse {

    public String token;
    public boolean active;
    public String first_name;
    public String last_name;
    public boolean uses_parent_account;
    public boolean corporate_card_holder;
    public Date created_time;
    public Date last_modified_time;
    public Metadata metadata;
    public String account_holder_group_token;
    public String status;
    public DepositAccount deposit_account;
}
