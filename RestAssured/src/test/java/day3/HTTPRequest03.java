package day3;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

//import io.restassured.internal.support.FileReader;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.io.FileReader;


public class HTTPRequest03 {
	
	//Path And Query Parameters
	
	//https://reqres.in/api/users?page=2&id=5
	@Test(priority=1)

	void addUser() {
		
	     given()
	         .pathParam("mypath","users")   //path parameters
	         .queryParam("page",2)   //query parameters
	         .queryParam("id",5)
	         //.body(data)
		
		.when()
		    .get("https://reqres.in/api/{mypath}")  //path parameters should be passed with curly brace.
		    
		    
		.then()
		  .statusCode(200)
		  .log().all();
		}
}
