package com.nimi.qe.api.util;

import com.nimi.qe.api.common.URIs;
import com.nimi.qe.api.marqeta.request.data.SandboxCredentials;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class RequestUtil {

    private RequestUtil(){

    }

    private static Response response;
    static SandboxCredentials sandboxCredentials = JsonReaderUtil.readSandboxCredentialsFromJsonFile();

    static RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(URIs.BASE_URI).setBasePath(URIs.BASE_PATH)
            .setContentType(ContentType.JSON).addHeader("Authorization", sandboxCredentials.getAuthorization())
            .build();

    public static Response sendPOSTRequestwithBasicAuth(String authUsername, String authPassword, Object jsonBody, String url){

        response = given().spec(requestSpecification).auth().basic(authUsername, authPassword)
                .body(jsonBody).when().post(url)
                .then().extract().response();
        return response;
    }

    public static Response sendPOSTRequest(Object jsonBody, String url){

        response = given().spec(requestSpecification)
                .body(jsonBody).when().post(url)
                .then().extract().response();
        return response;
    }

    public static Response sendGETRequest(Object jsonBody, String url){

        response = given().spec(requestSpecification)
                .body(jsonBody).when().get(url)
                .then().extract().response();
        return response;
    }
}
