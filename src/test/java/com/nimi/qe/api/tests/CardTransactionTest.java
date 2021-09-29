package com.nimi.qe.api.tests;

import com.nimi.qe.api.common.ResponseCodes;
import com.nimi.qe.api.common.ResponseMessages;
import com.nimi.qe.api.common.URIs;
import com.nimi.qe.api.marqeta.request.CardProducts;
import com.nimi.qe.api.marqeta.request.CardsRequest;
import com.nimi.qe.api.marqeta.request.Transactions;
import com.nimi.qe.api.marqeta.request.Users;
import com.nimi.qe.api.marqeta.request.data.*;
import com.nimi.qe.api.marqeta.response.CardResponse;
import com.nimi.qe.api.marqeta.response.UserResponse;
import com.nimi.qe.api.marqeta.response.data.CardProductsResponse;
import com.nimi.qe.api.marqeta.response.data.CardsResponse;
import com.nimi.qe.api.marqeta.response.data.TransactionResponse;
import com.nimi.qe.api.marqeta.response.data.UsersResponse;
import com.nimi.qe.api.util.JsonReaderUtil;
import com.nimi.qe.api.util.LoggerUtil;
import com.nimi.qe.api.util.ResponseUtil;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Random;

public class CardTransactionTest {

    Response response;
    User[] createUser;
    SandboxCredentials sandboxCredentials = new SandboxCredentials();
    Cards createCard = new Cards();
    Transaction transactions = new Transaction();
    InvalidUser invalidUser = new InvalidUser();
    Random random = new Random();
    int upperBound = 25;
    UsersResponse usersResponse = new UsersResponse();
    CardProductsResponse cardProductsResponse = new CardProductsResponse();
    CardsResponse cardsResponse = new CardsResponse();
    TransactionResponse transactionResponse = new TransactionResponse();
    private static final String STATUS = "ACTIVE";
    private static final boolean ACTIVE_STATUS = true;
    private static final String STATE = "ACTIVE";
    private static final String STATE_REASON = "New card activated";
    private static final String FULFILLMENT_REASON = "ISSUED";
    private static final String STATE_PENDING = "PENDING";
    private static final String STATE_DECLINED = "DECLINED";
    private static final String ERROR_MSG = "error_message";
    private static final double AMOUNT = 101;
    private String userToken = null;
    private String productToken = null;
    private String cardToken = null;

    @BeforeMethod
    public void initMethods(){
        sandboxCredentials = JsonReaderUtil.readSandboxCredentialsFromJsonFile();
        createUser = JsonReaderUtil.readUserDataFromJsonFile();
        transactions = JsonReaderUtil.readTransactionDataFromJsonFile();
        invalidUser = JsonReaderUtil.readInvalidUserDataFromJsonFile();
    }

    @Test(description = "FPTA-TC-1", priority = 1)
    public void createUserTest() throws IOException {
        SoftAssert softAssert = new SoftAssert();

        int randomNo = random.nextInt(upperBound);
        createUser[0].setFirst_name(createUser[0].getFirst_name() + randomNo);

        response = Users.createUser(createUser[0], sandboxCredentials.getUsername(), sandboxCredentials.getPassword(), URIs.USERS_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());

        usersResponse = ResponseUtil.deserializeTo(response.getBody().asString(), UsersResponse.class);

        softAssert.assertEquals(response.getStatusCode(), ResponseCodes.CODE_201, "Invalid Response Code!");
        softAssert.assertEquals(usersResponse.first_name, createUser[0].getFirst_name(), "User first name is invalid!");
        softAssert.assertEquals(usersResponse.last_name, createUser[0].getLast_name(), "User Last name is invalid!");
        softAssert.assertEquals(usersResponse.active, createUser[0].isActive(), "User is not Active!");
        softAssert.assertEquals(usersResponse.status, STATUS, "Status is invalid!");
        userToken = usersResponse.token;
        createCard.setUser_token(userToken);

        softAssert.assertAll();

    }

    @Test(description = "FPTA-TC-2", priority = 2, dependsOnMethods = "createUserTest")
    public void cardProductsTest() throws IOException {
        SoftAssert softAssert = new SoftAssert();

        response = CardProducts.cardProducts("", URIs.CARD_PRODUCTS_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());

        cardProductsResponse = ResponseUtil.deserializeTo(response.getBody().asString(), CardProductsResponse.class);

        softAssert.assertEquals(response.getStatusCode(), ResponseCodes.CODE_200, "Invalid Response Code!");
        softAssert.assertEquals(cardProductsResponse.data.get(0).active, ACTIVE_STATUS, "Card product is not Active!");
        productToken = cardProductsResponse.data.get(0).token;
        createCard.setCard_product_token(productToken);

        softAssert.assertAll();

    }

    @Test(description = "FPTA-TC-3", priority = 3, dependsOnMethods = {"createUserTest", "cardProductsTest"})
    public void createCardTest() throws IOException {
        SoftAssert softAssert = new SoftAssert();

        response = CardsRequest.createCard(createCard, URIs.CARD_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());

        cardsResponse = ResponseUtil.deserializeTo(response.getBody().asString(), CardsResponse.class);

        softAssert.assertEquals(response.getStatusCode(), ResponseCodes.CODE_201, "Invalid Response Code!");
        softAssert.assertEquals(cardsResponse.state, STATE, "Incorrect Card State!");
        softAssert.assertEquals(cardsResponse.state_reason, STATE_REASON, "Incorrect State Reason!");
        softAssert.assertEquals(cardsResponse.fulfillment_status, FULFILLMENT_REASON, "Incorrect Fulfillment Reason!");
        softAssert.assertEquals(cardsResponse.user_token, userToken, "Incorrect User Token!");
        softAssert.assertEquals(cardsResponse.card_product_token, productToken, "Incorrect Product Token!");
        cardToken = cardsResponse.token;
        transactions.setCard_token(cardToken);

        softAssert.assertAll();

    }

    @Test(description = "FPTA-TC-4", priority = 4, dependsOnMethods = {"createUserTest", "cardProductsTest", "createCardTest"})
    public void createTransactionTest() throws IOException {
        SoftAssert softAssert = new SoftAssert();

        transactions.setCard_token(cardToken);

        response = Transactions.createTransaction(transactions, URIs.TRANSACTION_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());

        transactionResponse = ResponseUtil.deserializeTo(response.getBody().asString(), TransactionResponse.class);

        softAssert.assertEquals(response.getStatusCode(), ResponseCodes.CODE_201, "Invalid Response Code!");
        softAssert.assertEquals(transactionResponse.transaction.card_token, cardToken, "Incorrect Card Token!");
        softAssert.assertEquals(transactionResponse.transaction.card_acceptor.mid, transactions.getMid(), "Incorrect MID!");
        softAssert.assertEquals(transactionResponse.transaction.request_amount, transactions.getAmount(), "Incorrect Amount!");
        softAssert.assertEquals(transactionResponse.transaction.state, STATE_PENDING, "Incorrect State!");

        softAssert.assertAll();

    }

    @Test(description = "FPTA-TC-5", priority = 5)
    public void createTransactionwithInactiveUserTest() throws IOException {
        SoftAssert softAssert = new SoftAssert();

        int randomNo = random.nextInt(upperBound);
        createUser[0].setFirst_name(createUser[0].getFirst_name() + randomNo);
        createUser[0].setActive(false);

        response = Users.createUser(createUser[0], sandboxCredentials.getUsername(), sandboxCredentials.getPassword(), URIs.USERS_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());

        usersResponse = ResponseUtil.deserializeTo(response.getBody().asString(), UsersResponse.class);
        userToken = usersResponse.token;
        createCard.setUser_token(userToken);

        response = CardProducts.cardProducts("", URIs.CARD_PRODUCTS_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());

        cardProductsResponse = ResponseUtil.deserializeTo(response.getBody().asString(), CardProductsResponse.class);
        productToken = cardProductsResponse.data.get(0).token;
        createCard.setCard_product_token(productToken);

        response = CardsRequest.createCard(createCard, URIs.CARD_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());

        cardsResponse = ResponseUtil.deserializeTo(response.getBody().asString(), CardsResponse.class);
        cardToken = cardsResponse.token;
        transactions.setCard_token(cardToken);

        transactions.setCard_token(cardToken);

        response = Transactions.createTransaction(transactions, URIs.TRANSACTION_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());

        transactionResponse = ResponseUtil.deserializeTo(response.getBody().asString(), TransactionResponse.class);

        softAssert.assertEquals(response.getStatusCode(), ResponseCodes.CODE_201, "Invalid Response Code!");
        softAssert.assertEquals(transactionResponse.transaction.card_token, cardToken, "Incorrect Card Token!");
        softAssert.assertEquals(transactionResponse.transaction.card_acceptor.mid, transactions.getMid(), "Incorrect MID!");
        softAssert.assertEquals(transactionResponse.transaction.request_amount, transactions.getAmount(), "Incorrect Amount!");
        softAssert.assertEquals(transactionResponse.transaction.state, STATE_DECLINED, "Incorrect State!");

        softAssert.assertAll();

    }

    @Test(description = "FPTA-TC-6", priority = 6)
    public void createUserWithInvalidDataTest() {
        SoftAssert softAssert = new SoftAssert();

        response = Users.createUser(invalidUser, sandboxCredentials.getUsername(), sandboxCredentials.getPassword(), URIs.USERS_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());


        softAssert.assertEquals(response.getStatusCode(), ResponseCodes.CODE_400, "Invalid Response Code!");
        softAssert.assertTrue(CardResponse.containsCardsErrorMessage(ResponseUtil.responseAsString(response, ERROR_MSG), ResponseMessages.MALFORMED_JSON_REQUEST_ERROR), "Invalid response error message!");

        response = Users.createUser("", sandboxCredentials.getUsername(), sandboxCredentials.getPassword(), URIs.USERS_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());


        softAssert.assertEquals(response.getStatusCode(), ResponseCodes.CODE_500, "Invalid Response Code!");
        softAssert.assertTrue(UserResponse.containsUserErrorMessage(ResponseUtil.responseAsString(response, ERROR_MSG), ResponseMessages.INTERNAL_SERVER_ERROR), "Invalid server error message!");

        softAssert.assertAll();

    }

    @Test(description = "FPTA-TC-7", priority = 7)
    public void createCardWithInvalidDataTest() {
        SoftAssert softAssert = new SoftAssert();

        createCard.setUser_token("");
        createCard.setCard_product_token("");
        response = CardsRequest.createCard(createCard, URIs.CARD_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());


        softAssert.assertEquals(response.getStatusCode(), ResponseCodes.CODE_400, "Invalid Response Code!");
        softAssert.assertTrue(UserResponse.containsUserErrorMessage(ResponseUtil.responseAsString(response, ERROR_MSG), ResponseMessages.INVALID_INPUTS_ERROR), "Invalid inputs error message!");

        response = CardsRequest.createCard("", URIs.CARD_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());


        softAssert.assertEquals(response.getStatusCode(), ResponseCodes.CODE_500, "Invalid Response Code!");
        softAssert.assertTrue(UserResponse.containsUserErrorMessage(ResponseUtil.responseAsString(response, ERROR_MSG), ResponseMessages.INTERNAL_SERVER_ERROR), "Invalid server message!");

        softAssert.assertAll();

    }

    @Test(description = "FPTA-TC-8", priority = 8)
    public void createTransactionsWithInvalidDataTest() {
        SoftAssert softAssert = new SoftAssert();

        transactions.setCard_token("");
        response = Transactions.createTransaction(transactions, URIs.TRANSACTION_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());


        softAssert.assertEquals(response.getStatusCode(), ResponseCodes.CODE_400, "Invalid Response Code!");
        softAssert.assertTrue(UserResponse.containsUserErrorMessage(ResponseUtil.responseAsString(response, ERROR_MSG), ResponseMessages.INVALID_INPUTS_ERROR), "Invalid inputs error message!");

        response = Transactions.createTransaction("", URIs.TRANSACTION_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());


        softAssert.assertEquals(response.getStatusCode(), ResponseCodes.CODE_500, "Invalid Response Code!");
        softAssert.assertTrue(UserResponse.containsUserErrorMessage(ResponseUtil.responseAsString(response, ERROR_MSG), ResponseMessages.INTERNAL_SERVER_ERROR), "Invalid server message!");

        softAssert.assertAll();

    }

    @Test(description = "FPTA-TC-9", priority = 9, dependsOnMethods = {"createUserTest", "cardProductsTest", "createCardTest"})
    public void createTransactionExceedBoundaryTest() throws IOException {
        SoftAssert softAssert = new SoftAssert();

        transactions.setCard_token(cardToken);
        transactions.setAmount(AMOUNT);

        response = Transactions.createTransaction(transactions, URIs.TRANSACTION_PATH);
        LoggerUtil.logInfo(response.body().prettyPrint());

        transactionResponse = ResponseUtil.deserializeTo(response.getBody().asString(), TransactionResponse.class);

        softAssert.assertEquals(response.getStatusCode(), ResponseCodes.CODE_201, "Invalid Response Code!");
        softAssert.assertEquals(transactionResponse.transaction.card_token, cardToken, "Incorrect Card Token!");
        softAssert.assertEquals(transactionResponse.transaction.card_acceptor.mid, transactions.getMid(), "Incorrect MID!");
        softAssert.assertEquals(transactionResponse.transaction.request_amount, transactions.getAmount(), "Incorrect Amount!");
        softAssert.assertEquals(transactionResponse.transaction.state, STATE_DECLINED, "Incorrect State!");

        softAssert.assertAll();

    }

}
