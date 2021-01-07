import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import files.payLoad;
import files.resources;

public class basics3 {
	
	Properties prop= new Properties();
	@BeforeTest
	public void getData() throws IOException {
		
		FileInputStream fis= new FileInputStream("C:\\Users\\123\\eclipse-workspace\\TestingREST\\src\\files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void postGet() 
	{
		RestAssured.baseURI=prop.getProperty("HOST");
		
		Response res=given().
		queryParam("key",prop.getProperty("KEY")).
		body(payLoad.getPostData()).
		when().
		post(resources.placePostData()).
		then().
		assertThat().statusCode(200).and().
	    contentType(ContentType.JSON).and().
	    body("status", equalTo("INVALID_REQUEST")).
	    extract().response();
		
		String resString=res.asString();
		
		System.out.println(resString);
		
		JsonPath js= new JsonPath(resString);
		
		//System.out.println(js.get("status").toString());
		
		String status=js.get("status");
		
		given().
		queryParam("key","someKey").
		body("{\r\n" + 
				"                    \"lat\": 56.476378,\r\n" + 
				"                    \"" + status + "\": 84.9505741\r\n" + 
				"                }").
		when().
		post("/delete_resourse").
		then().
		assertThat().statusCode(404).and().
		header("Server","sffe");
		
		
		
		
		
	}

}
