package com.nimi.qe.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;

public class ResponseUtil {

    public static <T> T deserializeTo(String response, Class<T> toType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, toType);
    }

    public static String responseAsString(Response response, String responseField) {
        JsonPath jsonPath = response.jsonPath();
        String responseString = jsonPath.getString(responseField);
        return responseString;
    }
}
