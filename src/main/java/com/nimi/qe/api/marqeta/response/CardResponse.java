package com.nimi.qe.api.marqeta.response;

public class CardResponse {

    public static boolean containsCardsErrorMessage(String actual, String expected) {
        return actual.contains(expected);
    }
}
