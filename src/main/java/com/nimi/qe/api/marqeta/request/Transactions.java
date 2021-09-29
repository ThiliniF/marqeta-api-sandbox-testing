package com.nimi.qe.api.marqeta.request;

import com.nimi.qe.api.util.RequestUtil;
import io.restassured.response.Response;

public class Transactions {

    private Transactions(){

    }

    private static Response response;

    public static Response createTransaction(Object requestBody, String url) {
        response = RequestUtil.sendPOSTRequest(requestBody, url);
        return response;
    }
}
