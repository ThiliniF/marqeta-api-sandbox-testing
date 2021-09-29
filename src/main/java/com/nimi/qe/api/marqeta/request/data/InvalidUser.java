package com.nimi.qe.api.marqeta.request.data;

import lombok.Data;

@Data
public class InvalidUser {

    private String name;
    private String last_name;
    private boolean active;
}
