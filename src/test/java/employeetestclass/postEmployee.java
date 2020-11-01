package employeetestclass;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import employeebase.baseclass;
import employeeutiles.Restutils;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class postEmployee extends baseclass {
	
	String empname = Restutils.empName();
	String empjob = Restutils.empJob();
	
	@BeforeClass
	void getallemployees() throws InterruptedException
	{
		
		logger.info("------get all employess ----");
		RestAssured.baseURI= "https://reqres.in";
		
		httprequest = RestAssured.given();
		JSONObject requestparams = new JSONObject();
		requestparams.put("name",empname);
		requestparams.put("job",empjob);
		
		httprequest.header("Content-Type", "application/json");
		httprequest.body(requestparams.toJSONString());
		
		response = httprequest.request(Method.POST,"/api/users");
		
		
		Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("------respose body ----");
		String responseBody = response.getBody().asString();		
		System.out.println("response body is : "+ responseBody );
		Assert.assertEquals(responseBody.contains(empname),true);
		Assert.assertEquals(responseBody.contains(empjob),true);
	}
	@Test
	void checkStatuscode()
	{
		logger.info("------status code ----");
		int statuscode = response.getStatusCode();
		logger.info("statuscode  is : "+ statuscode );
		Assert.assertEquals(statuscode, 201);
		
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
		//Assert.assertEquals(contentencoding, "gzip");
		
	}
	
	@AfterClass
	void teardown()
	{
		logger.info("------finished ----");
	}

}
