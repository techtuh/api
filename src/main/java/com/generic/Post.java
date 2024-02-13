package com.generic;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Post {
	
	Response responseObj ;
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeTest
	public void getSetup() {
		//data convert  in json format
		JsonObject jo = new JsonObject();
		jo.addProperty("name", " Ahad");
		jo.addProperty("salary", 5000);
		
		//call request(request)
		RequestSpecification rs = RestAssured.given();
		// data add inside body/
		rs.body(jo.toString());
		//add header
		rs.headers("Content-Type", "application/json");
		//send request to server/app with post URL and return respose
		responseObj =	rs.post("https://httpbin.org/post");
	    System.out.println(responseObj.print());
	}
	

	  @Test(priority=0, description="check status code",groups={"unit testing", "regression testing"} )
	  public void getStatCode() { 
		  System.out.println("Status code = "+responseObj.statusCode());
		  softAssert.assertEquals(responseObj.statusCode(), 200); 
	//  sf.assertAll(); 
	  }
	  
	  @Test(priority=0, description="response tiome <2000",groups={"unit testing", "regression testing"} )
	  public void getResponseTime() { 
		  
		  System.out.println("Response time= " +responseObj.time());
		  softAssert.assertTrue(responseObj.time()<2000);
	  
	  }
	  
	  @Test(priority=0, description="ContentType should be json",groups={"unit testing", "regression testing"} )
	  public void getContentType() { 
		  System.out.println("Content Type ="+responseObj.contentType());
		  softAssert.assertEquals(responseObj.contentType(), "application/json");
		  //softAssert.assertEquals(responseObj.contentType(), "application/json");
	//  sf.assertTrue(resp.contentType().contains("json")); 
	  
	  }
	  
	  @Test (priority=0, description="body should not be null",groups={"unit testing", "regression testing"} )
	  public void getBodyNotNull() {
		  softAssert.assertTrue(!responseObj.body().toString().equals(null));  
		  
	  //System.out.println(!resp.body().toString().equals(null));
	  System.out.println("This is body = " +responseObj.body().print());
	  
	  }
	 
	
	
	  @Test (priority=0, description="check Attribute key",groups={"unit testing", "regression testing"} )
	public void getAttributeKey() {
		softAssert.assertTrue(responseObj.body().asString().contains("name"));
		System.out.println("AttributeKey "+ responseObj.print());
	
	}
	 @Test (priority=0, description="check Attribute value",groups={"unit testing", "regression testing"} )
public void getAttributeValue() {
	//value check enter -> inside Json
	// get value need help -> Json parser
JsonPath jsonpath =	responseObj.jsonPath();
		 
		 //JsonPath jp =responseObj.jsonPath();
//json.name = json is patent
		softAssert.assertEquals(jsonpath.get("json.name"), " Ahad");
		//System.out.println(jsonpath.prettyPrint());
		//System.out.println(jp.get);
		responseObj.print();
	//softAssert.assertAll();
	}
	
	@AfterTest
	public void tearDown() {
		
		softAssert.assertAll();
	}
	
//public void  () {}
		
//public void () {}
	
//public void () {}

//public void () {}
	
	
}


