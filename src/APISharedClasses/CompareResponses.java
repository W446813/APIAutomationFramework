package APISharedClasses;

import io.restassured.response.Response;

import java.io.IOException;
import java.util.Objects;

public class CompareResponses {

    Logs logs = new Logs();
    int responseCode;

    public CompareResponses() throws IOException {
    }

    public void compare(Response response,int expectedResponseCode ) throws Exception {
        responseCode = response.getStatusCode();
        System.out.println(responseCode);
        response.prettyPrint();
        if (responseCode == expectedResponseCode) {
            logs.CapturedLogs(logs.PASS, "Response code found is: " + responseCode);
        } else {
            logs.CapturedLogs(logs.FAIL, "Expected: "+ expectedResponseCode+ " Actual: " + responseCode);
        }
        if (Objects.equals(response.getBody().asString(), "")){
            logs.CapturedLogs(logs.INFO,"Response body is empty");
        } else {
            logs.CapturedLogs(logs.INFO,"Response is: "+ response.getBody().asString());
        }

    }

}
