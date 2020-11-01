package employeeutiles;

import org.apache.commons.lang3.RandomStringUtils;

public class Restutils {
	
	public static String empName()
	{
		String generatedString  = RandomStringUtils.randomAlphabetic(1);
		return ("vicky"+generatedString);
	}
	public static String empJob()
	{
		String generatedString  = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}
	public static String empAge()
	{
		String generatedString  = RandomStringUtils.randomAlphabetic(2);
		return (generatedString);
	}
}
