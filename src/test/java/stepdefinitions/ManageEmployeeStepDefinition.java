package stepdefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.junit.Assert;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class ManageEmployeeStepDefinition {

    private RequestSpecification request;
    private Response response;
    public ValidatableResponse json;
    WireMockServer wireMockServer;

    @Before
    public void setup () {
        wireMockServer = new WireMockServer(8090);
        wireMockServer.start();
    }

    @After
    public void teardown () {
        wireMockServer.stop();
    }

    public void setupStub(String apiMethod,String apiName,String employeeName) {
        if(apiMethod.equals("GET")){
            wireMockServer.stubFor(get(urlEqualTo("/"+apiName+"/"+employeeName))
                    .willReturn(aResponse().withHeader("Content-Type", "text/plain")
                            .withStatus(200)
                            .withBodyFile("/json/getemployee.json")));
        } else if (apiMethod.equals("PUT")){
            wireMockServer.stubFor(put(urlEqualTo("/"+apiName+"/"+employeeName))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/xml")
                            .withBodyFile("/json/postemployee.json")));
        } else if (apiMethod.equals("DELETE")){
            wireMockServer.stubFor(delete(urlEqualTo("/"+apiName+"/"+employeeName))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/xml")));
        }
    }

    @Given("^create a stub for \"([^\"]*)\" request with an \"([^\"]*)\" \"([^\"]*)\"$")
    public void create_stub_for_request(String apiMethod,String apiName,String employeeName) throws Throwable {
        setupStub(apiMethod,apiName,employeeName);
    }

    @Then("^a users makes a \"([^\"]*)\" request with an \"([^\"]*)\" \"([^\"]*)\"$")
    public void trigger_api_call_for_method(String apiMethod,String apiName,String employeeName) throws Throwable {
        request = given().pathParam("employee_name",employeeName);
        if(apiMethod.equals("GET")){
            response = request.when().get("http://localhost:8090/"+apiName+"/{employee_name}");
        }else if (apiMethod.equals("PUT")){
            JSONObject requestParams = new JSONObject();
            requestParams.put("employeename", "shabnamfathima");
            response = request.body(requestParams.toJSONString())
                    .when().put("http://localhost:8090/"+apiName+"/{employee_name}");
        }else if (apiMethod.equals("DELETE")){
            response = request.when().delete("http://localhost:8090/"+apiName+"/{employee_name}");
        }
    }

    @When("^validate the status code is \"([^\"]*)\"$")
    public void status_code(String statusCode) throws Throwable {
        json = response.then().statusCode(Integer.parseInt(statusCode));
    }

    @And("^response includes the name \"([^\"]*)\" and value \"([^\"]*)\"$")
    public void a_employee_gets_request(String jsonName,String JsonValue) throws Throwable {
        String title = response.jsonPath().get(jsonName);
        System.out.println(title);
        Assert.assertEquals(JsonValue, title);
    }

}
