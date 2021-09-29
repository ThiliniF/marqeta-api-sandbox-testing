package com.nimi.qe.api.marqeta.request;

import com.nimi.qe.api.util.RequestUtil;
import io.restassured.response.Response;

public class CardsRequest {

    private CardsRequest(){

    }

    public static Response createCard(Object requestBody, String url) {
        Response response = RequestUtil.sendPOSTRequest(requestBody, url);
        return response;
    }
}
