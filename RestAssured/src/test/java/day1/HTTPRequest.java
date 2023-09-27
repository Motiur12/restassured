package day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequest {
	int id; // Comes from post method Add users.
	
	@Test(priority=1)

		void getUser() {
			
			given()
			
			.when()
			   .get("https://reqres.in/api/users?page=2")
			
			.then()
			   .statusCode(200)
			   .body("page",equalTo(2))
			   .log().all();
			
			
		}
	
	
	@Test(priority=2)

	void addUser() {
		
		HashMap data=new HashMap();
		data.put("name","pavan");
		data.put("job","trainer");
		
		
		id=given()
		     .contentType("application/json")
		     .body(data)
		
		.when()
		    .post("https://reqres.in/api/users")
		    .jsonPath().getInt("id");
		    
		/*.then()
		  .statusCode(201)
		  .log().all();
		}*/
	
	} 
	
    @Test(priority=3,dependsOnMethods={"addUser"})
    void update() {
		
		HashMap data=new HashMap();
		data.put("name","pavan");
		data.put("job","Shopkeeper");
		
		
		given()
		   .contentType("application/json")
		   .body(data)
		
		.when()
		    .put("https://reqres.in/api/users/"+id)
		   
		    
		.then()
		   .statusCode(200)
		   .log().all();
		}
    
    @Test(priority=4)
    void delete() {
		
	
		
		given()
		   
		
		.when()
		    .delete("https://reqres.in/api/users/"+id)
		   
		    
		.then()
		   .statusCode(204);
		   
		}
    
}