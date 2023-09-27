package day2;



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


public class HTTPRequest02 {
	
		
		//Post request Using Hashmap
		//@Test(priority=1)

		void addUser() {
			
			HashMap data=new HashMap();
			data.put("name","pavan");
			data.put("job","trainer");
			
			
		    String courseARR[]= {"C","C++"};
		     
		    data.put("courses", courseARR);
		    
		    given()
		         .contentType("application/json")
		         .body(data)
			
			.when()
			    .post("https://reqres.in/api/users")
			    
			    
			.then()
			  .statusCode(201)
			  .body("name",equalTo("pavan"))
			  .body("job",equalTo("trainer"))
			  .body("courses[0]",equalTo("pavan"))
			  .body("courses[1]",equalTo("C++"))
			  .header("Content-Type","application/jso; charset=utf-8")
			  .log().all();
			}
		

		//Post Request body using org.json library
		
		//@Test
		void addUser1() {
			
			JSONObject data=new JSONObject();
			data.put("name","pavan");
			data.put("job","trainer");
			
			String courseARR[]= {"C","C++"};
			data.put("courses", courseARR);
			
		    
		    given()
		         .contentType("application/json")
		         .body(data.toString())
			
			.when()
			    .post("https://reqres.in/api/users")
			    
			    
			.then()
			  .statusCode(201)
			  .body("name",equalTo("pavan"))
			  .body("job",equalTo("trainer"))
			  .body("courses[0]",equalTo("pavan"))
			  .body("courses[1]",equalTo("C++"))
			  .header("Content-Type","application/jso; charset=utf-8")
			  .log().all();
			}
		
		
		//Post Request body using POJO
		
		@Test
		void addUser2() {
			
			POJO data = new POJO();
			
			data.setName("Scott5");
			data.setJob("UnderTaker2");
			
		    //String x=data.getName();
			
			//String courseARR[]= {"C","C++"};
			//data.setCourses(courseARR);
		    
		    given()
		         .contentType("application/json")
		         .body(data)
			
			.when()
			    .post("https://reqres.in/api/users")
			    
			    
			.then()
			  .statusCode(201)
			  .body("name",equalTo(data.getName()))
			  .body("job",equalTo(data.getJob()))
			 // .body("courses[0]",equalTo("pavan"))
			  //.body("courses[1]",equalTo("C++"))
			  //.header("Content-Type","application/jso; charset=utf-8")
			  .log().all();
			}		
		
		//Post Request body using External File
		//@Test
		void addUser3() throws FileNotFoundException{
			
			File f = new File(".\\body.json");
			FileReader fr = new FileReader(f);
			
			JSONTokener jt=new JSONTokener(fr);
			
			JSONObject data=new JSONObject(jt);
			
			
			
			
		    given()
		         .contentType("application/json")
		         .body(data.toString())
			
			.when()
			    .post("https://reqres.in/api/users")
			    
			    
			.then()
			  .statusCode(201)
			  .body("name",equalTo("Motiur"))
			  .body("job",equalTo("Undertaker"));
			  //.body("courses[0]",equalTo("pavan"))
			  //.body("courses[1]",equalTo("C++"))
			  //.header("Content-Type","application/jso; charset=utf-8")
			  //.log().all();
			}

}


