package serialization;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Serialization_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com  ";

		AddPlace a = new AddPlace();
		a.setAccuracy(50);
		a.setName("Frontline house");
		a.setPhone_number("(+91) 983 893 3937");
		a.setAddress("29, side layout, cohen 09");
		a.setWebsite("http://google.com");
		a.setLanguage("French-IN");

		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		a.setTypes(mylist);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		a.setLocation(l);

		Response res = given().log().all().queryParam("key", "qaclick123")
				.body(a)
				.when()	.post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response();

		String responseString = res.asString();
		System.out.println(responseString);

	}
}
