import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.resources;
import files.ReusableMethods;


public class basics_xml {
	
	Properties prop= new Properties();
	@BeforeTest
	public void getData() throws IOException {
		
		FileInputStream fis= new FileInputStream("C:\\Users\\123\\eclipse-workspace\\TestingREST\\src\\files\\env.properties");
		prop.load(fis);
	}
	
	
	@Test
	public void Test() throws IOException {
		// TODO Auto-generated method stub
		
		//BaseURL or Host
				RestAssured.baseURI=prop.getProperty("HOST");
				String postData=ReusableMethods.GenerateStringFromResource("C:\\Users\\123\\eclipse-workspace\\TestingREST\\src\\post.xml");
				
				Response res=given().
				       param("location","56.4977,84.9744").
				       param("radius","10000").
				       param("key",prop.getProperty("KEY")).
				       param("keyword","Университет").
				       when().
				       get(resources.placeGettData()).
				       then().assertThat().statusCode(200).
				       and().contentType(ContentType.XML).
				       and().body("PlaceSearchResponse.result[0].name", equalTo("Tomsk Polytechnic University")).
				       and().body("PlaceSearchResponse.result[0].place_id", equalTo("ChIJjW_d9KiUJkMRl90p7T0_oY8")).
				       and().header("Server", "scaffolding on HTTPServer2").
				       extract().response();
				
				XmlPath x=ReusableMethods.rawToXML(res);
				System.out.println(x.getString("PlaceSearchResponse.result[0].name"));
		
				
				//System.out.println(resStr);
		

	}
	
	
	

}
