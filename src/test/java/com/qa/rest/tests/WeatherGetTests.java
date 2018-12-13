package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherGetTests {

	
	@Test
	public void getWeatherDetailsWithCorrectCityNameTest(){
		
		//1. define the base URL
		//http://restapi.demoqa.com/utilities/weather/city
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		//2. Define the http request:
		RequestSpecification httpRequest = RestAssured.given();
		
		//3. Make Request/Execute the request
		Response response = httpRequest.request(Method.GET,"/Pune");
		
		//4. Get the Response Body
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is "+responseBody);
		
		//Validate city name or Validate the key or value
		Assert.assertEquals(responseBody.contains("Pune"), true);
		
		//5. Get the Status Code and Validate it
		int statuscode = response.getStatusCode();
		System.out.println("The status code is :"+statuscode);
		
		//6. Validate it
		Assert.assertEquals(statuscode, 200);
		
		System.out.println("The status line is "+response.getStatusLine());
		
		//7. Get the headers
		Headers headers = response.getHeaders();
		System.out.println(headers);
		
		String contentType = response.getHeader("Content-Type");
		System.out.println("The value of Content Type is: "+contentType);
		
		String contentLength = response.getHeader("Content-Length");
		System.out.println("The value of Content Length is: "+contentLength);
		
		//Get the key/node value by using JSONPath
		JsonPath jsonPathValue = response.jsonPath();
		String city = jsonPathValue.get("City");
		System.out.println("The value of City is: "+city);

		String temperature = jsonPathValue.get("Temperature");
		System.out.println("The value of Temperature is: "+temperature);
		
		String humidity = jsonPathValue.get("Humidity");
		System.out.println("The value of Humidity is: "+humidity);
		
		String weatherDescription = jsonPathValue.get("WeatherDescription");
		System.out.println("The value of WeatherDescription is: "+weatherDescription);
		
		String windSpeed = jsonPathValue.get("WindSpeed");
		System.out.println("The value of WindSpeed is: "+windSpeed);
		
		String windDirectionDegree = jsonPathValue.get("WindDirectionDegree");
		System.out.println("The value of WindDirectionDegree is: "+windDirectionDegree);

		
		
	}
	
	
	@Test
	public void getWeatherDetailsWithInCorrectCityNameTest(){
		
		//1. define the base URL
		//http://restapi.demoqa.com/utilities/weather/city
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		//2. Define the http request:
		RequestSpecification httpRequest = RestAssured.given();
		
		//3. Make Request/Execute the request
		Response response = httpRequest.request(Method.GET,"/test123");
		
		//4. Get the Response Body
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is "+responseBody);
		
		//Validate city name or Validate the key or value
		Assert.assertEquals(responseBody.contains("internal error"), true);
		
		//5. Get the Status Code and Validate it
		int statuscode = response.getStatusCode();
		System.out.println("The status code is :"+statuscode);
		
		//6. Validate it
		Assert.assertEquals(statuscode, 400);
		
		System.out.println("The status line is "+response.getStatusLine());
		
		
	}
	
}
