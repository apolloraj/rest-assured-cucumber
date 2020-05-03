package stepdefinitions;

import net.minidev.json.JSONObject;

import java.util.Map;

public class CreateEmployee {

    public JSONObject getEmployeeRequest(Map<String,String> data){
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", Integer.parseInt(data.get("id")));
        requestParams.put("employeename",data.get("employeeName"));
        requestParams.put("firstName", data.get("firstName"));
        requestParams.put("lastName", data.get("lastName"));
        requestParams.put("email", data.get("email"));
        requestParams.put("password", data.get("password"));
        requestParams.put("phone", data.get("phone"));
        requestParams.put("employeeStatus", Integer.parseInt(data.get("employeeStatus")));
        return requestParams;
    }

}
