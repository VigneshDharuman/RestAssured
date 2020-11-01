package employeebase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class baseclass {
	
	public static RequestSpecification httprequest;
	public static Response response;
	public String id = "2";
	public Logger logger;
	
	
	@BeforeClass
	public void setup() {
		
		logger = Logger.getLogger("EmplyeesRestAPI");
		PropertyConfigurator.configure("Log4J.properties");
		logger.setLevel(Level.DEBUG);
		
		
	}

}
