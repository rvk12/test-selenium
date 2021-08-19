package net.test.selenium;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;

public class RestAssuredTest {

    @Test
    public void postmanFirstGetTest(){
        RestAssured.
                when().get("https://postman-echo.com/get?foo1=bar1&foo2=bar2").
                then().assertThat().statusCode(200).
                and().body("args.foo2", is("bar2"));
    }

    @Test
    public void postRequestExampleTest() {
        String someRandomString = String.format("%1$TH%1$TM%1$TS", new Date());
        JSONObject requestBody = new JSONObject();
        requestBody.put("FirstName", someRandomString);
        requestBody.put("LastName", someRandomString);
        requestBody.put("UserName", someRandomString);
        requestBody.put("Password", someRandomString);
        requestBody.put("Email", someRandomString + "@gmail.com");
        JSONObject person = new JSONObject();
        person.put("FirstName2", "name");
        person.put("LastName2", "macho");
        requestBody.put("person2", person);
        JSONArray ja = new JSONArray();
        ja.put("name");
        ja.put("noname");
        person.put("val", ja); // "val": ["name", "noname"],
        RequestSpecification request = RestAssured.given();
//                RestAssured.given().request().header().request().body()
//                .when().post()
//                .then().assertThat().getStatusCode();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("https://webhook.site/ce1357f3-47de-406d-ac45-5b449c82a767");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println("The status code recieved: " + statusCode);
    }
}
