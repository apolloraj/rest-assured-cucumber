package stepdefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

import net.minidev.json.JSONObject;

public class CreateEmployeeStepDefinition {
    private RequestSpecification request;
    private Response response;
    public ValidatableResponse json;
    public WireMockServer wireMockServer;

    @Before
    public CreateEmployeeStepDefinition setup () {
        wireMockServer = new WireMockServer(8091);
        wireMockServer.start();
        return this;
    }

    @After
    public CreateEmployeeStepDefinition teardown () {
        wireMockServer.stop();
        wireMockServer.resetAll();
        return this;
    }

    public void setupStub(String strApiName) {
        wireMockServer.stubFor(post(urlEqualTo("/"+strApiName))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/xml")));
    }

    @Given("^create a stub for a create employee \"([^\"]*)\"$")
    public void create_stub_create_employee(String strApiName) throws Throwable {
        setupStub(strApiName);
    }

    @Then("^send a create employee request \"([^\"]*)\" with the details \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void a_employee_gets_request(String apiName,String id,String employeeName,String firstName,String lastName,String email,String password,String phone,String employeeStatus) throws Throwable {
        request = given().contentType(ContentType.JSON);
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", Integer.parseInt(id));
        requestParams.put("employeename", employeeName);
        requestParams.put("firstName", firstName);
        requestParams.put("lastName", lastName);
        requestParams.put("email", email);
        requestParams.put("password", password);
        requestParams.put("phone", phone);
        requestParams.put("employeeStatus", Integer.parseInt(employeeStatus));
        response = request.body(requestParams.toJSONString())
                .when().post("http://localhost:8091/"+apiName);
    }

    @When("^the POST status code is 200$")
    public void status_code() throws Throwable {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

}
