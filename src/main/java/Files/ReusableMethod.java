package Files;

import io.restassured.path.json.JsonPath;

public class ReusableMethod 
{

	
	public static JsonPath rawTojSon(String responseadd)
	{
		JsonPath js=new JsonPath(responseadd);
		
		return js;
	}
}
