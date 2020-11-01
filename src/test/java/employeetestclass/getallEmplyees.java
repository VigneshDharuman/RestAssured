package employeetestclass;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import employeebase.baseclass;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class getallEmplyees extends baseclass {
	
	@BeforeClass
	void getallemployees() throws InterruptedException
	{
		
		logger.info("------get all employess ----");
		RestAssured.baseURI= "https://reqres.in";
		
		httprequest = RestAssured.given();
		
		response = httprequest.request(Method.GET,"/api/users?page=2");
		Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("------respose body ----");
		String responseBody = response.getBody().asString();		
		System.out.println("response body is : "+ responseBody );
		Assert.assertTrue(responseBody!=null);
		
	}
	@Test
	void checkStatuscode()
	{
		logger.info("------status code ----");
		int statuscode = response.getStatusCode();
		logger.info("statuscode  is : "+ statuscode );
		Assert.assertEquals(statuscode, 200);
		
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
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	void checkContenttype()
	{
		logger.info("------content type is ----");
		String contenttype = response.header("Content-Type");
		logger.info("contenttype  is ==> "+ contenttype );
		Assert.assertEquals(contenttype, "application/json; charset=utf-8");
		
	}
	
	@Test
	void checkServertype()
	{
		logger.info("------Server type is ----");
		String servertype = response.header("Server");
		logger.info("Servertype  is ==> "+ servertype );
		Assert.assertEquals(servertype, "cloudflare");
		
	}
	
	@Test
	void checkContentEncoding()
	{
		logger.info("------ContentEncoding is ----");
		String contentencoding = response.header("Content-Encoding");
		logger.info("Servertype  is ==> "+ contentencoding );
		Assert.assertEquals(contentencoding, "gzip");
		
	}
	
	@AfterClass
	void teardown()
	{
		logger.info("------finished ----");
	}
	

	
	
}
