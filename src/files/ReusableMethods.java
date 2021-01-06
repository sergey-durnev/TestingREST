package files;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	public static String GenerateStringFromResource (String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	public static XmlPath rawToXML(Response r)
	{
	
		
		String respon=r.asString();
		XmlPath x=new XmlPath(respon);
		return x;
		
	}
	
	public static JsonPath rawToJson(Response r)
	{ 
		String respon=r.asString();
		JsonPath x=new JsonPath(respon);
		return x;
	}
	
	public static String getSessionKey() {
		//Properties prop= new Properties();
		RestAssured.baseURI=("http://localhost:8080");
		Response res=given().
		header("Content-Type","application/json").
		body("{\r\n"
				+ "    \"username\": \"anrugoog\",\r\n"
				+ "    \"password\": \"Jira01\"\r\n"
				+ "}").
		when().
		post("/rest/auth/1/session").
		then().
		statusCode(200).
		extract().response();
		
		JsonPath js=ReusableMethods.rawToJson(res);
		String sessionid=js.get("session.value");
		//System.out.println(sessionid);
		return(sessionid);
	}

}
