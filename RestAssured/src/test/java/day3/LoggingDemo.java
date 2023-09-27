package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoggingDemo {
	
	
	@Test(priority=1)
    void testLogs() {
		
	     given()
	         
		
		.when()
		    .get("https://reqres.in/api/users?page=2")  //path parameters should be passed with curly brace.
		    
		    
		.then()
		 
		  //.log().all();
		  //.log().body(); //print only body
		   //.log().cookies();
		   .log().headers();
		}
}
	



