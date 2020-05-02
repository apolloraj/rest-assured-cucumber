package stepdefinitions;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class LoginEmployeeStepDefinition {
    private RequestSpecification request;
    private Response response;
    public ValidatableResponse json;
    public WireMockServer wireMockServer;

    @Before
    public LoginEmployeeStepDefinition setup () {
        wireMockServer = new WireMockServer(8093);
        wireMockServer.start();
        return this;
    }

    @After
    public LoginEmployeeStepDefinition teardown () {
        wireMockServer.stop();
        wireMockServer.resetAll();
        return this;
    }

    public void setupStub(String userRequest,String apiName,String userName,String userPassword) {
        if(userRequest.equals("login")){
            wireMockServer.stubFor(get(urlEqualTo("/"+apiName+"?employeename="+userName+"&password="+userPassword))
                    .willReturn(aResponse().withHeader("Content-Type", "text/plain")
                            .withStatus(200)));
        } else if (userRequest.equals("logout")){
            wireMockServer.stubFor(get(urlEqualTo("/"+apiName))
                    .willReturn(aResponse().withHeader("Content-Type", "text/plain")
                            .withStatus(200)));
        }

    }

    @Given("^create a stub for a \"([^\"]*)\" request with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void create_login_Stub(String userRequest,String apiName,String userName,String userPassword) throws Throwable {
        setupStub(userRequest,apiName,userName,userPassword);
    }

    @Then("^the status code \"([^\"]*)\" is returned$")
    public void response_status_code(String statusCode) throws Throwable {
        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, Integer.parseInt(statusCode));
    }

    @When("^a users makes a \"([^\"]*)\" request with a \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void login_employee(String userRequest,String apiName,String userName,String userPassword) throws Throwable {
        if(userRequest.equals("login")){
            request = given().param("employeename",userName)
                    .param("password",userPassword);
            response = request.when().get("http://localhost:8093/"+apiName);
        } else {
            request = given().contentType(ContentType.JSON);
            response = request.when().get("http://localhost:8093/"+apiName);
        }

    }

}
