package com.nimi.qe.api.marqeta.request.data;

import lombok.Data;

@Data
public class User {

    private String first_name;
    private String last_name;
    private boolean active;
}
