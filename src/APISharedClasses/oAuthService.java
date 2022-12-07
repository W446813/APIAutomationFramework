package APISharedClasses;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;

public class oAuthService {

    Logs logs = new Logs();
    Response response;

    public oAuthService() throws IOException {
    }
    public String getAccessToken_clientCredentials_stage() throws Exception {
        response = RestAssured.given()
                .header("Accept","application/json")
                .header("Authorization","Basic cWEtY2xpZW50OmZ6czFjeUJTc09memFqc0RQbHY0OUNPVWRqVFRXYUlN")
                .header("Content-Type","application/x-www-form-urlencoded")
                .urlEncodingEnabled(true)
                .formParam("grant_type", "client_credentials")
                .relaxedHTTPSValidation().when()
                .post("https://wmd-stage.stg-internal.wexfleetservice.com/oauth-service/uaa/oauth/token");
        if(response.getStatusCode() == 200){
            logs.CapturedLogs(logs.PASS,"The response code is " + response.getStatusCode() + ". Access token obtained.");
            return response.jsonPath().getString("access_token");
        }else{
            logs.CapturedLogs(logs.FAIL,"The response code for is " + response.getStatusCode());
            return null;
        }
    }
    public String getAccessToken_password_stage() throws Exception {
        response = RestAssured.given()
                .header("Accept","application/json")
                .header("Authorization","Basic cWEtY2xpZW50OmZ6czFjeUJTc09memFqc0RQbHY0OUNPVWRqVFRXYUlN")
                .header("Content-Type","application/x-www-form-urlencoded")
                .urlEncodingEnabled(true)
                .formParam("grant_type", "password")
                .formParam("scope","read")
                .formParam("username","7c02c2e0-af61-435c-aafc-1fd38a562cb3")
                .formParam("password","0509")
                .relaxedHTTPSValidation().when()
                .post("https://wmd-stage.stg-internal.wexfleetservice.com/oauth-service/uaa/oauth/token");
        if(response.getStatusCode() == 200){
            logs.CapturedLogs(logs.PASS,"The response code is " + response.getStatusCode() + ". Access token obtained.");
            return response.jsonPath().getString("access_token");
        }else{
            logs.CapturedLogs(logs.FAIL,"The response code for is " + response.getStatusCode());
            return null;
        }
    }
}
