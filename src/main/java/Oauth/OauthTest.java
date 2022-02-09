package Oauth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class OauthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// WebDriverManager.chromedriver().setup();

		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "D:\\SOFTWARES\\Work Softwares\\sel\\chromedriver_win32\\chromedriver.exe");
		 * 
		 * WebDriver driver = new ChromeDriver();
		 * 
		 * driver.get(
		 * "https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&flowName=GeneralOAuthFlow"
		 * );
		 * 
		 * driver.findElement(By.id("identifierId")).sendKeys("mayur.kh005@gmail.com");
		 * driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button")).click(
		 * );
		 * driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"))
		 * .sendKeys("marotikaka@123");
		 * driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button")).click();
		 */

		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWgwN-7oXavCcsm4S8ZAViw6bTgAUsLNMqK5595D93prd3ZEFZegwDC6ItpfGjNT6A&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";

		String partialtext = url.split("code=")[1];
		String code = partialtext.split("&scope")[0];
		System.out.println(code);

		String accesstokenResponse = given().urlEncodingEnabled(false).queryParams("code", code)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		System.out.println(accesstokenResponse);
		JsonPath js = new JsonPath(accesstokenResponse);
		String accesstoken = js.getString("access_token");
		System.out.println(accesstoken);

		String actualresponse = given().queryParams("access_token", accesstoken).when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();

		System.out.println(actualresponse);

	}

}
