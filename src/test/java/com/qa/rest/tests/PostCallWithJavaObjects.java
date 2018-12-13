package com.qa.rest.tests;

import java.util.Date;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.rest.objects.CustomerResponseFailure;
import com.qa.rest.objects.CustomerResponseSuccess;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostCallWithJavaObjects {
	
	//INT: Types of Authentication
	//1. Username and password
	//2. Auth Token
	//3. Secret Key/Token/Session ID/Customer ID/Consumer Key
	//4. Security based questions
	
	@Test
	public void createCustomerTest() {

		// 1. define the base URL
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
 
		// 2. Define the http request:
		RequestSpecification httpRequest = RestAssured.given();

		// 3. Create a JSON Object with all the required fields

		//To genearte unique data always
		
		JSONObject requestJson = new JSONObject();
		requestJson.put("FirstName", "Mii"); //append with current date and time to genearte unique data always
		requestJson.put("LastName", "Mig");
		requestJson.put("UserName", "mii001");
		requestJson.put("Password", "miimig");
		requestJson.put("Email", "mii@gmail");

		// 4. Add header:
		httpRequest.header("Content-Type", "application/json");

		// 5. Add the JSON Payload to the body of the request
		httpRequest.body(requestJson.toJSONString());

		// 6. Post the request and get the response
		Response response = httpRequest.post("/register");

		// 7. Get the Response Body
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is " + responseBody);
		
		//Deserilization of the response into CustomerResponse Class
		
		if(response.statusCode()==201) {
		CustomerResponseSuccess customerResponse = response.as(CustomerResponseSuccess.class);
		
		System.out.println(customerResponse.SuccessCode);
		System.out.println(customerResponse.Message);
		
		Assert.assertEquals("OPERATION_SUCCESS", customerResponse.SuccessCode);
		Assert.assertEquals("Operation completed successfully", customerResponse.Message);
		}
		
		else if(response.statusCode()==200) {
			CustomerResponseFailure customerResponse = response.as(CustomerResponseFailure.class);
			System.out.println(customerResponse.FaultId);
			System.out.println(customerResponse.fault);
			
			Assert.assertEquals("User already exists", customerResponse.FaultId);
			Assert.assertEquals("FAULT_USER_ALREADY_EXISTS", customerResponse.fault);
		}
		
	}
	
	
	

}
