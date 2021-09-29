package com.nimi.qe.api.marqeta.response;

public class UserResponse {

    private UserResponse(){

    }

    public static boolean containsUserErrorMessage(String actual, String expected) {
        return actual.contains(expected);
    }
}
