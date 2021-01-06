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


public class jiraApi2 {
	
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
				+ "    \"body\": \"Rest Assured\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").
		when().post("/rest/api/2/issue/RA-5/comment").
		then().
		statusCode(201).
		extract().response();
		
		JsonPath js=ReusableMethods.rawToJson(res);
		String author=js.get("author.name");
		int commentId=js.getInt("id");
		System.out.println(author+"  "+commentId);
		
		res=given().
				header("Content-Type","application/json").
				header("Cookie","JSESSIONID="+ReusableMethods.getSessionKey()).
				body("{\r\n"
						+ "    \"body\": \"Can't believe!\",\r\n"
						+ "    \"visibility\": {\r\n"
						+ "        \"type\": \"role\",\r\n"
						+ "        \"value\": \"Administrators\"\r\n"
						+ "    }\r\n"
						+ "}").
				when().put("/rest/api/2/issue/RA-5/comment/"+commentId).
				then().
				statusCode(200).
				extract().response();
				
				js=ReusableMethods.rawToJson(res);
				String bodyText=js.get("body");
				System.out.println(bodyText);
	
		
	}

}
