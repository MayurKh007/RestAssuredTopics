package JiraTestApis;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://localhost:8080/";

		SessionFilter session = new SessionFilter();

		String response = given().log().all().header("content-type", "application/json")
				.body("{ \"username\": \"Mayur\", \"password\": \"Mayur123\" }").log().all().filter(session).when()
				.post("rest/auth/1/session").then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		//Add Comment

		given().pathParam("key", "10100").log().all().header("content-type", "application/json")
				.body("{\r\n" + "    \"body\": \"This is my first comment via automation.\",\r\n"
						+ "    \"visibility\": {\r\n" + "        \"type\": \"role\",\r\n"
						+ "        \"value\": \"Administrators\"\r\n" + "    }\r\n" + "}")
				.filter(session).when().post("rest/api/2/issue/{key}/comment").then().log().all().assertThat()
				.statusCode(201);

		//Add attachment

		given().header("X-Atlassian-Token", "no-check").filter(session).pathParam("key", "10100")
				.header("Content-Type", "multipart/form-data").multiPart("file", new File("Attachment.txt")).when()
				.post("rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
		
		
	String issuedetails= given().filter(session).pathParam("key","10100").queryParam("filed", "comment").log().all().then().log().all()
		.when().get(" /rest/api/2/issue/{Key}").then().log().all().extract().response().asString();
	System.out.println(issuedetails);
	
	JsonPath js = new JsonPath(issuedetails); 
	

		

	}

}
