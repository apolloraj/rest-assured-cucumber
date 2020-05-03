package stepdefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONArray;
import org.junit.Assert;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CreateEmployeeStepDefinition<Emp> {
    private RequestSpecification request;
    private Response response;
    public WireMockServer wireMockServer;
    public List<JSONObject> list;
    public JSONArray empArray;
    private Emp[] array;

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

    @Then("^generate a list of employee request with list$")
    public void a_employee_request_list(DataTable table) throws Throwable {
        JSONObject requestParams;
        list = new ArrayList<JSONObject>();
        CreateEmployee employee = new CreateEmployee();
        for (Map<String, String> data : table.asMaps(String.class, String.class)) {
            requestParams = employee.getEmployeeRequest(data);
            list.add(requestParams);
        }
    }

    @Then("^generate a list of employee request with array$")
    public <Emp> void a_employee_request_array(DataTable table) throws Throwable {
        JSONObject requestParams;
        empArray=new JSONArray();
        CreateEmployee employee = new CreateEmployee();
        for (Map<String, String> data : table.asMaps(String.class, String.class)) {
            requestParams = employee.getEmployeeRequest(data);
            empArray.add(requestParams);
        }
    }

    @And("^send the POST request to \"([^\"]*)\" for Employee list$")
    public void send_request(String apiName) throws Throwable {
        request = given().contentType(ContentType.JSON);
        response = request.body(list)
                .when().post("http://localhost:8091/"+apiName);
    }

    @And("^send the POST request to \"([^\"]*)\" for Employee array$")
    public void send_request_array(String apiName) throws Throwable {
        request = given().contentType(ContentType.JSON);
        response = request.body(empArray)
                .when().post("http://localhost:8091/"+apiName);
    }

    @When("^the POST status code is \"([^\"]*)\"$")
    public void status_code(String statusCode) throws Throwable {
        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, Integer.parseInt(statusCode));
    }

}


