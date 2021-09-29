package com.nimi.qe.api.marqeta.request;

import com.nimi.qe.api.util.RequestUtil;
import io.restassured.response.Response;

public class CardsRequest {

    private CardsRequest(){

    }

    private static Response response;

    public static Response createCard(Object requestBody, String url) {
        response = RequestUtil.sendPOSTRequest(requestBody, url);
        return response;
    }
}
