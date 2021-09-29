package com.nimi.qe.api.marqeta.request;

import com.nimi.qe.api.util.RequestUtil;
import io.restassured.response.Response;

public class CardProducts {

    private static Response response;

    public static Response cardProducts(String body, String url) {
        response = RequestUtil.sendGETRequest(body, url);
        return response;
    }
}
