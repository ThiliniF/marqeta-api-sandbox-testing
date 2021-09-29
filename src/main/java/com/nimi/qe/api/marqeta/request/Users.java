package com.nimi.qe.api.marqeta.request;

import com.nimi.qe.api.util.RequestUtil;
import io.restassured.response.Response;


public class Users {

    private Users(){

    }

    private static Response response;

    public static Response createUser(Object requestBody, String authUsername, String authPassword, String url) {
        response = RequestUtil.sendPOSTRequestwithBasicAuth(authUsername, authPassword, requestBody, url);
        return response;
    }
}
