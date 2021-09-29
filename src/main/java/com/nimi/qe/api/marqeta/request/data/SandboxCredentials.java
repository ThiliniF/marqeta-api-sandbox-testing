package com.nimi.qe.api.marqeta.request.data;

import lombok.Data;

@Data
public class SandboxCredentials {
    private String username;
    private String password;
    private String authorization;
}
