package jsonparse;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.ReusableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Dynamicjson {

	@Test(dataProvider="BooksData")
	public void addbook(String isbn,String aisle) {

		RestAssured.baseURI = "http://216.10.245.166";

		String responseadd = given().log().all().header("Content-Type", "application/json")
				.body(Payload.Addbook(isbn, aisle)).when().post("/Library/Addbook.php").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath js = ReusableMethod.rawTojSon(responseadd);

		String id = js.get("ID");

		System.out.println(id);

	}
	
	//Delete API
	

	//Multiple data testing with Data provider annotation
	@DataProvider(name="BooksData")
	public Object[][] getbookdata()
	{
		return new Object[][] {{"India","0001"},{"Aus","0002"},{"NZ","0003"},{"SA","0002"}};
		 
	}

}
