package day3;



import org.testng.annotations.Test;

import io.restassured.response.Response;

//import io.restassured.internal.support.FileReader;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;



public class cookiesdemo {
	//@Test
	void addUser13() {
		
	     given()
	         
		
		.when()
		    .get("https://www.google.com/")  
		    
		    
		.then()
		  .cookie("AEC","AUEFqZdHZ_rtubljcPITUjQLFo3JJlmYfgs_mOeEhfKFp4XAUVHXwkQmdg")
		  .log().all();
		}
	
	
		@Test
		void cookiesinfo() {
			
		    Response res= given()
		         
			
			.when()
			    .get("https://www.google.com/");  
			    
			 //get single cookie info
		    
		     /*String cookie_value=res.getCookie("AEC");
		     System.out.println("Value of the cookie is=====>"+cookie_value);*/
		    
		    //get all the cookies information
		    
		    Map<String,String> cookie_value=res.getCookies();
		    
		    //System.out.println(cookie_value.keySet());
		    
		    for (String k:cookie_value.keySet() ) {
		    	
		    	String cookies_value=res.getCookie(k);
		    	System.out.println(k+"         "+cookies_value);
		    	
		    }
		    
		    
		    } 

}
