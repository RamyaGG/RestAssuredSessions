package com.qa.rest.tests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateCustomerPostCall {

	@Test
	public void createCustomerTest() {

		// 1. define the base URL
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		// 2. Define the http request:
		RequestSpecification httpRequest = RestAssured.given();

		// 3. Create a JSON Object with all the required fields

		//To genearte unique data always
		Date date = new Date();
		System.out.println(date);

		JSONObject requestJson = new JSONObject();
		requestJson.put("FirstName", "Mii" + date); //append with current date and time to genearte unique data always
		requestJson.put("LastName", "Mig" + date);
		requestJson.put("UserName", "mii001" + date);
		requestJson.put("Password", "miimig" + date);
		requestJson.put("Email", "mii@gmail" + date);

		// 4. Add header:
		httpRequest.header("Content-Type", "application/json");

		// 5. Add the JSON Payload to the body of the request
		httpRequest.body(requestJson.toJSONString());

		// 6. Post the request and get the response
		Response response = httpRequest.post("/register");

		// 7. Get the Response Body
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is " + responseBody);

		// 8. Get the Status Code and Validate it
		int statuscode = response.getStatusCode();
		System.out.println("The status code is :" + statuscode);

		// Validate it
		Assert.assertEquals(statuscode, 201);

		System.out.println("The status line is " + response.getStatusLine());

		// 9. Get the headers
		Headers headers = response.getHeaders();
		System.out.println(headers);

		String contentType = response.getHeader("Content-Type");
		System.out.println("The value of Content Type is: " + contentType);

		String contentLength = response.getHeader("Content-Length");
		System.out.println("The value of Content Length is: " + contentLength);

	}

}
