package employeetestclass;



import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import employeebase.baseclass;
import employeeutiles.Restutils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class deleteEmployees  extends baseclass{
	
	
	
	@BeforeClass
	void getallemployees() throws InterruptedException
	{
		
		logger.info("------get all employess ----");
		RestAssured.baseURI= "https://reqres.in";
		
		httprequest = RestAssured.given();
		
		//geting all user 
		response = httprequest.request(Method.GET,"/api/users?page=2");
		String responseBody = response.getBody().asString();		
		System.out.println("response body is : "+ responseBody );
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		//identifing by the id
	
		
        String empid = jsonPathEvaluator.get("[0].id");
			
		response = httprequest.request(Method.DELETE,"/api/users/2/"+empid);
		
		
		Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("------respose body ----");
		String responseBody = response.getBody().asString();		
		System.out.println("response body is : "+ responseBody );
		//Assert.assertEquals(responseBody.contains("successfully! deleted Records"),true);
	
	}
	@Test
	void checkStatuscode()
	{
		logger.info("------status code ----");
		int statuscode = response.getStatusCode();
		logger.info("statuscode  is : "+ statuscode );
		//Assert.assertEquals(statuscode, 200);
		
	}

	@Test
	void checkResponsetime()
	{
		logger.info("------resposne time----");
		
		long responsetime = response.getTime();
		logger.info("responsetime  is  ==> "+ responsetime );
		if(responsetime>2000)
			logger.warn("responsetime  is greater than 2000 " );
		
		Assert.assertTrue(responsetime<2000);
		
	}
	
	@Test
	void checkStatusline()
	{
		logger.info("------status line ----");
		String statusline = response.getStatusLine();
		logger.info("statusline  is ==> "+ statusline );
		//Assert.assertEquals(statusline, "HTTP/1.1 201 OK");
		
	}
	
	@Test
	void checkContenttype()
	{
		logger.info("------content type is ----");
		String contenttype = response.header("Content-Type");
		logger.info("contenttype  is ==> "+ contenttype );
		//Assert.assertEquals(contenttype, "application/json; charset=utf-8");
		
	}
	
	@Test
	void checkServertype()
	{
		logger.info("------Server type is ----");
		String servertype = response.header("Server");
		logger.info("Servertype  is ==> "+ servertype );
		//Assert.assertEquals(servertype, "cloudflare");
		
	}
	
	@Test
	void checkContentEncoding()
	{
		logger.info("------ContentEncoding is ----");
		String contentencoding = response.header("Content-Encoding");
		logger.info("Servertype  is ==> "+ contentencoding );
		//Assert.assertEquals(contentencoding, "gzip");
		
	}
	
	@AfterClass
	void teardown()
	{
		logger.info("------finished ----");
	}
}
