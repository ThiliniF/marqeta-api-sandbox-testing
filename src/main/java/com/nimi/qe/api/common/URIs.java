package com.nimi.qe.api.common;

import lombok.Data;

@Data
public class URIs {

    private URIs(){

    }

    public static final String BASE_URI = "https://sandbox-api.marqeta.com";
    public static final String BASE_PATH = "/v3";
    public static final String USERS_PATH = "/users";
    public static final String CARD_PRODUCTS_PATH = "/cardproducts";
    public static final String CARD_PATH = "/cards";
    public static final String TRANSACTION_PATH = "/simulate/authorization";
}
