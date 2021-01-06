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


public class jiraApi1 {
	
	Properties prop= new Properties();
	@BeforeTest
	public void getData() throws IOException {
		
		FileInputStream fis= new FileInputStream("C:\\Users\\123\\eclipse-workspace\\testingAutomation\\src\\files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	
	public void JiraAPI(){
		 
		//Creating Session
		RestAssured.baseURI=("http://localhost:8080");
		Response res=given().
		header("Content-Type","application/json").
		header("Cookie","JSESSIONID="+ReusableMethods.getSessionKey()).
		body("{\r\n"
				+ "    \r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\": {\r\n"
				+ "            \"id\": \"10001\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"The Bug 3-5\",\r\n"
				+ "        \"issuetype\": {\r\n"
				+ "            \"id\": \"10004\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}").
		when().post("/rest/api/2/issue").
		then().
		statusCode(201).
		extract().response();
		
		JsonPath js=ReusableMethods.rawToJson(res);
		String issueId=js.get("key");
		System.out.println(issueId);
		

	
		
	}

}
