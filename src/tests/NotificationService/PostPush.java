package tests.NotificationService;

import APISharedClasses.CompareResponses;
import APISharedClasses.Logs;
import APISharedClasses.oAuthService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

public class PostPush {

    oAuthService oAuth = new oAuthService();
    Logs logs = new Logs();
    String  accessToken,
            server = "https://wmd-stage.stg-internal.wexfleetservice.com/notification-service/v1",
            endPoint = "/push",
            url = server+endPoint;
    int expectedResponseCode;
    Response response;
    CompareResponses compare = new CompareResponses();

    public PostPush() throws IOException {
    }

    @Test(priority = 0, groups = "ReadyForCICD")
    public void getOAuthToken() throws Exception {
        logs.setupTestClass("POST - /push");
        logs.setupTestYellow("Fetching test data");
        System.out.println("Test setup started");
        accessToken = oAuth.getAccessToken_clientCredentials_stage();
        System.out.println("Test Setup completed");
    }
    @Test(priority = 1, groups = "ReadyForCICD")
    public void allRequiredInfo() throws Exception {
        expectedResponseCode = 200;
        logs.setupTestYellow("TC#1 - " + expectedResponseCode);
        logs.CapturedLogs(logs.INFO, "This request is executed with all needed information.");
        logs.CapturedLogs(logs.INFO,"Executing request: "+url);
        response = RestAssured.given()
                .header("Accept","application/json")
                .header("Authorization","Bearer "+accessToken)
                .header("Content-Type","application/json")
                .header("Cache-Control","no-cache")
                .body("{\"application\":\"driver-dash\"," +
                        "\"targetAppIdentity\":\"Yo\"," +
                        "\"messageBody\":\"string\"," +
                        "\"messageTitle\":\"string\"," +
                        "\"deepLink\":\"string\"," +
                        "\"badgeCount\":0}")
                .relaxedHTTPSValidation().when()
                .post(url);
        compare.compare(response,expectedResponseCode);
    }
    @Test(priority = 2, groups = "ReadyForCICD")
    public void withoutAcceptHeader() throws Exception {
        expectedResponseCode = 200;
        logs.setupTestYellow("TC#2 - " + expectedResponseCode);
        logs.CapturedLogs(logs.INFO, "This request is executed without Accept header.");
        logs.CapturedLogs(logs.INFO,"Executing request: "+url);
        response = RestAssured.given()
                //.header("Accept","application/json")
                .header("Authorization","Bearer "+accessToken)
                .header("Content-Type","application/json")
                .header("Cache-Control","no-cache")
                .body("{\"application\":\"driver-dash\"," +
                        "\"targetAppIdentity\":\"Yo\"," +
                        "\"messageBody\":\"string\"," +
                        "\"messageTitle\":\"string\"," +
                        "\"deepLink\":\"string\"," +
                        "\"badgeCount\":0}")
                .relaxedHTTPSValidation().when()
                .post(url);
        compare.compare(response,expectedResponseCode);
    }
    @Test(priority = 3, groups = "ReadyForCICD")
    public void withoutAuthorizationHeader() throws Exception {
        expectedResponseCode = 401;
        logs.setupTestYellow("TC#3 - " + expectedResponseCode);
        logs.CapturedLogs(logs.INFO, "This request is executed without Authorization header.");
        logs.CapturedLogs(logs.INFO,"Executing request: "+url);
        response = RestAssured.given()
                .header("Accept","application/json")
                //.header("Authorization","Bearer "+accessToken)
                .header("Content-Type","application/json")
                .header("Cache-Control","no-cache")
                .body("{\"application\":\"driver-dash\"," +
                        "\"targetAppIdentity\":\"Yo\"," +
                        "\"messageBody\":\"string\"," +
                        "\"messageTitle\":\"string\"," +
                        "\"deepLink\":\"string\"," +
                        "\"badgeCount\":0}")
                .relaxedHTTPSValidation().when()
                .post(url);
        compare.compare(response,expectedResponseCode);
    }
    @Test(priority = 4, groups = "ReadyForCICD")
    public void withoutContentTypeHeader() throws Exception {
        expectedResponseCode = 415;
        logs.setupTestYellow("TC#4 - " + expectedResponseCode);
        logs.CapturedLogs(logs.INFO, "This request is executed without Content-Type header.");
        logs.CapturedLogs(logs.INFO,"Executing request: "+url);
        response = RestAssured.given()
                .header("Accept","application/json")
                .header("Authorization","Bearer "+accessToken)
                //.header("Content-Type","application/json")
                .header("Cache-Control","no-cache")
                .body("{\"application\":\"driver-dash\"," +
                        "\"targetAppIdentity\":\"Yo\"," +
                        "\"messageBody\":\"string\"," +
                        "\"messageTitle\":\"string\"," +
                        "\"deepLink\":\"string\"," +
                        "\"badgeCount\":0}")
                .relaxedHTTPSValidation().when()
                .post(url);
        compare.compare(response,expectedResponseCode);
    }
    @Test(priority = 5, groups = "ReadyForCICD")
    public void withoutCacheControlHeader() throws Exception {
        expectedResponseCode = 200;
        logs.setupTestYellow("TC#5 - " + expectedResponseCode);
        logs.CapturedLogs(logs.INFO, "This request is executed without Cache-Control header.");
        logs.CapturedLogs(logs.INFO,"Executing request: "+url);
        response = RestAssured.given()
                .header("Accept","application/json")
                .header("Authorization","Bearer "+accessToken)
                .header("Content-Type","application/json")
                //.header("Cache-Control","no-cache")
                .body("{\"application\":\"driver-dash\"," +
                        "\"targetAppIdentity\":\"Yo\"," +
                        "\"messageBody\":\"string\"," +
                        "\"messageTitle\":\"string\"," +
                        "\"deepLink\":\"string\"," +
                        "\"badgeCount\":0}")
                .relaxedHTTPSValidation().when()
                .post(url);
        compare.compare(response,expectedResponseCode);
    }
    @Test(priority = 6, groups = "ReadyForCICD")
    public void withoutBody() throws Exception {
        expectedResponseCode = 400;
        logs.setupTestYellow("TC#6 - " + expectedResponseCode);
        logs.CapturedLogs(logs.INFO, "This request is executed without body.");
        logs.CapturedLogs(logs.INFO,"Executing request: "+url);
        response = RestAssured.given()
                .header("Accept","application/json")
                .header("Authorization","Bearer "+accessToken)
                .header("Content-Type","application/json")
                .header("Cache-Control","no-cache")
//                .body("{\"application\":\"driver-dash\"," +
//                        "\"targetAppIdentity\":\"Yo\"," +
//                        "\"messageBody\":\"string\"," +
//                        "\"messageTitle\":\"string\"," +
//                        "\"deepLink\":\"string\"," +
//                        "\"badgeCount\":0}")
                .relaxedHTTPSValidation().when()
                .post(url);
        compare.compare(response,expectedResponseCode);
    }
    @Test(priority = 7, groups = "ReadyForCICD")
    public void withWrongBodyParams() throws Exception {
        expectedResponseCode = 400;
        logs.setupTestYellow("TC#7 - " + expectedResponseCode);
        logs.CapturedLogs(logs.INFO, "This request is executed with wrong body params.");
        logs.CapturedLogs(logs.INFO,"Executing request: "+url);
        response = RestAssured.given()
                .header("Accept","application/json")
                .header("Authorization","Bearer "+accessToken)
                .header("Content-Type","application/json")
                .header("Cache-Control","no-cache")
                .body("{\"Wrongapplication\":\"driver-dash\"," +
                        "\"WrongtargetAppIdentity\":\"Yo\"," +
                        "\"WrongmessageBody\":\"string\"," +
                        "\"WrongmessageTitle\":\"string\"," +
                        "\"WrongdeepLink\":\"string\"," +
                        "\"WrongbadgeCount\":0}")
                .relaxedHTTPSValidation().when()
                .post(url);
        compare.compare(response,expectedResponseCode);
    }
    @Test(priority = 8, groups = "ReadyForCICD")
    public void withWrongBodyValues() throws Exception {
        expectedResponseCode = 400;
        logs.setupTestYellow("TC#8 - " + expectedResponseCode);
        logs.CapturedLogs(logs.INFO, "This request is executed with wrong body values.");
        logs.CapturedLogs(logs.INFO,"Executing request: "+url);
        response = RestAssured.given()
                .header("Accept","application/json")
                .header("Authorization","Bearer "+accessToken)
                .header("Content-Type","application/json")
                .header("Cache-Control","no-cache")
                .body("{\"application\":\"testApp\"," +
                        "\"targetAppIdentity\":\"15\"," +
                        "\"messageBody\":\"string\"," +
                        "\"messageTitle\":\"string\"," +
                        "\"deepLink\":\"15\"," +
                        "\"badgeCount\":\"zero\"}")
                .relaxedHTTPSValidation().when()
                .post(url);
        compare.compare(response,expectedResponseCode);
    }
}
