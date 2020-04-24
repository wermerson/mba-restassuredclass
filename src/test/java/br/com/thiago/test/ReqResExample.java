package br.com.thiago.test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import io.restassured.http.ContentType;

public class ReqResExample {

	@Before
	public void configuraApi() {
		baseURI = 	"https://reqres.in/";

	}
	@Test
	public void methodGet() {
		given()
		.when()
		.get("/api/users/{id}", 2)
		.then().contentType(ContentType.JSON)
		.statusCode(HttpStatus.SC_OK);
	}
	@Test
	public void methodPost() {
		
		basePath= "/api/users";		
		given()
			.contentType("application/json")
		.body("{\"name\": \"morpheus\",\"job\": \"leader\"}")
		.when()
			.post("/")
		.then()
			.statusCode(HttpStatus.SC_CREATED);
	}
}
