package day4;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class parsingresbody {
	
	
	//By using json Object we can parsing the body.
	
	//@Test
	void testJsonresponse() {
		
		//Approach1    
		
	   given()
	       .contentType(ContentType.JSON)
	       
	       
	   .when()
	        .get("https://reqres.in/api/users?page=2")
	   
	   .then()
		    .statusCode(200)
		    .header("Content-Type","application/json; charset=utf-8")
	        .body("book[3].title",equalTo("The Lord of the Rings"));
	}
	
	//@Test
	void testJsonresponse1() {
		
		
		
		//Approach2
		
	   Response res=given()
	       .contentType(ContentType.JSON)
	       
	       
	   .when()
	        .get("https://reqres.in/api/users?page=2");
	   
	   Assert.assertEquals(res.getStatusCode(),200); //validation
	   Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");   //validation
	   
	   String bookname = res.jsonPath().get("book[3].title").toString();
	   Assert.assertEquals(bookname, "The Lord of the Rings"); 
	}
	
	    @Test
		void testJsonresponsedata() {
			
			
			
			//Approach3
			
	    	Response res=   given()
		                        .contentType(ContentType.JSON)
		       
		       
		   .when()
		        .get("https://reqres.in/api/users?page=2");
		   
		 
		   JSONObject jo=new JSONObject(res.asString());  //converting response to json object type.
		   
		   //Search for title of the book in the json.
		   boolean status=false;
		   
		   for(int i=0 ; i<jo.getJSONArray("book").length(); i++ ) {
			   
			 String bookTitle =  jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			 if(bookTitle.equals("The Lord Of the rings")){
				 status =true;
				 break;
			 }
		   }
		   
		   Assert.assertEquals(status, true);
		   
		   
		   //validate total price of the books
		   
		   double totalprice =0;
		   
		   for(int i=0 ; i<jo.getJSONArray("book").length(); i++ ) {
			   
				 String price =  jo.getJSONArray("book").getJSONObject(i).get("price").toString();
				 totalprice =totalprice + Double.parseDouble(price);
				 }
			   System.out.println("Total price of the books is: "+totalprice);
			   Assert.assertEquals(totalprice, 53.92);
		}
}
