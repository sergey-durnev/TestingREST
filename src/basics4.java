import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.ReusableMethods;


public class basics4 {
	
	Properties prop= new Properties();
	@BeforeTest
	public void getData() throws IOException {
		
		FileInputStream fis= new FileInputStream("C:\\Users\\123\\eclipse-workspace\\TestingREST\\src\\files\\env.properties");
		prop.load(fis);
	}
	
	
	@Test
	public void Test() {
		// TODO Auto-generated method stub
		
		//BaseURL or Host
				RestAssured.baseURI=prop.getProperty("HOST");
				
				Response res=given().
				       param("location","56.4977,84.9744").
				       param("radius","10000").
				       param("key",prop.getProperty("KEY")).
				       param("keyword","Университет").
				       log().all().
				       when().
				       get("/maps/api/place/nearbysearch/json").
				       then().
				       log().ifValidationFails().
				       assertThat().statusCode(200).
				       and().contentType(ContentType.JSON).
				       and().body("results[0].name", equalTo("Tomsk Polytechnic University")).
				       and().body("results[0].place_id", equalTo("ChIJjW_d9KiUJkMRl90p7T0_oY8")).
				       and().header("Server", "scaffolding on HTTPServer2").
				       extract().response();
				JsonPath js=ReusableMethods.rawToJson(res);
				int count=js.get("results.size()");
				for (int i=0;i<count;i++)
				{
					System.out.println(js.getString("results["+i+"].name"));
				}
				
				System.out.println(count);
		

	}
	

}
