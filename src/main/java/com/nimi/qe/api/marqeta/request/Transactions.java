package com.nimi.qe.api.marqeta.request;

import com.nimi.qe.api.util.RequestUtil;
import io.restassured.response.Response;

public class Transactions {

    private Transactions(){

    }

    public static Response createTransaction(Object requestBody, String url) {
        Response response = RequestUtil.sendPOSTRequest(requestBody, url);
        return response;
    }
}
