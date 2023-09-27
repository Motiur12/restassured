package day5_ParsingXMLResponse;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.json.JSONObject;

public class ParsingXMLResponse {

	//@Test
	void testXMLResponse() {
		
		
		//Approach1
		
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page",equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name",equalTo("Developer")); 
	}
	
	    //@Test
		void testXMLResponse1() {
		
		
		//Approach 2 return the response as a variable.
			
		Response res=given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		
		
		String pageno = res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageno, "1");
		
		String travelName=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travelName, "Developer");
	}
	
	    
	    @Test
		void testXMLResponse2() {
		
		
		//Approach 3 XMLObj.
			
		Response res=given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlobj = new XmlPath(res.asString());
		
		//Verify total number of travelers from the response
		
		List<String> travellers=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		
		Assert.assertEquals(travellers.size(),10);
		
		//Verify traveler name is present in the response
		
		List<String> travellers_name=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		
		boolean status=false;
		for(String travellersname:travellers_name) {
			//System.out.println(travellersname);
			if (travellersname.equals("Developer")) {
				status =true;
				break;
			}
		}
	    Assert.assertEquals(status, true);
	    }
	
}
