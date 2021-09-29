package com.nimi.qe.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimi.qe.api.common.JsonFilePaths;
import com.nimi.qe.api.marqeta.request.data.InvalidUser;
import com.nimi.qe.api.marqeta.request.data.Transaction;
import com.nimi.qe.api.marqeta.request.data.User;
import com.nimi.qe.api.marqeta.request.data.SandboxCredentials;

import java.io.IOException;
import java.nio.file.Paths;

public class JsonReaderUtil {

    private JsonReaderUtil(){

    }

    private static final String IO_EXCEPTION = "IO Exception";

    public static SandboxCredentials readSandboxCredentialsFromJsonFile(){
        ObjectMapper objectMapper = new ObjectMapper();
        SandboxCredentials sandboxCredentials = null;
        try {
            sandboxCredentials = objectMapper.readValue(Paths.get(JsonFilePaths.FILE_PATH_SANDBOX_CREDENTIALS).toFile(), SandboxCredentials.class);
        } catch (IOException e) {
            LoggerUtil.logError(IO_EXCEPTION, e.fillInStackTrace());
        }
        return sandboxCredentials;
    }

    public static User[] readUserDataFromJsonFile(){
        ObjectMapper objectMapper = new ObjectMapper();
        User[] createUser = null;
        try {
            createUser = objectMapper.readValue(Paths.get(JsonFilePaths.FILE_PATH_CREATE_USER).toFile(), User[].class);
        } catch (IOException e) {
            LoggerUtil.logError(IO_EXCEPTION, e.fillInStackTrace());
        }
        return createUser;
    }

    public static Transaction readTransactionDataFromJsonFile(){
        ObjectMapper objectMapper = new ObjectMapper();
        Transaction transaction = null;
        try {
            transaction = objectMapper.readValue(Paths.get(JsonFilePaths.FILE_PATH_TRANSACTION).toFile(), Transaction.class);
        } catch (IOException e) {
            LoggerUtil.logError(IO_EXCEPTION, e.fillInStackTrace());
        }
        return transaction;
    }

    public static InvalidUser readInvalidUserDataFromJsonFile(){
        ObjectMapper objectMapper = new ObjectMapper();
        InvalidUser createUser = null;
        try {
            createUser = objectMapper.readValue(Paths.get(JsonFilePaths.FILE_PATH_INVALID_USER).toFile(), InvalidUser.class);
        } catch (IOException e) {
            LoggerUtil.logError(IO_EXCEPTION, e.fillInStackTrace());
        }
        return createUser;
    }

}
