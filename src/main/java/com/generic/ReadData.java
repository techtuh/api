package com.generic;



import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReadData {
	
	 Response resp;
	 SoftAssert sf= new SoftAssert();
	 
	@BeforeTest
	public void getSetup() {
	 
		
		resp= RestAssured.get("https://httpbin.org/get");
	}
	
	
	  @Test public void getStatCode() { 
		  System.out.println("Status code = "+resp.statusCode());
	  sf.assertEquals(resp.statusCode(), 200); 
	//  sf.assertAll(); 
	  }
	  
	  @Test public void getResponseTime() { 
		  
		  System.out.println("Response time= " +resp.time());
	  sf.assertTrue(resp.time()<2000);
	  
	  }
	  
	  @Test public void getContentType() { 
		  System.out.println("Content Type ="+resp.contentType());
	  sf.assertEquals(resp.contentType(), "application/json");
	//  sf.assertTrue(resp.contentType().contains("json")); 
	  
	  }
	  
	  @Test() public void getBodyNotNull() {
	  //System.out.println(!resp.body().toString().equals(null));
	  System.out.println("This is body = " +resp.body().print());
	  
	  }
	 
	
	
	
	public void getAttributeKey() {
		
		System.out.println("AttributeKey "+ resp.body().toString().contains("url"));
	
	}
	@Test
public void getAttributeValue() {
	//value check enter -> inside Json
	// get value need help -> Json parser
		JsonPath jp = resp.jsonPath();
		sf.assertEquals(jp.get("url"), "https://httpbin.org/get");
		
		//System.out.println(jp.get);
	
	}
	
	@AfterTest
	public void teaDown() {
		
		//sf.assertAll();
	}
}
