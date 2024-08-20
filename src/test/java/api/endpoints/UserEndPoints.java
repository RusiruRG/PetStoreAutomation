package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// perform crud operations - create, retrieve , update, delete operations

public class UserEndPoints {

	// post request
	public static Response createUser(User payload){
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(Routes.post_url);
		
		return response;
	}
	
	// get user request
	public static Response readUser(String userName){
			
			Response response = given()
				.pathParam("username", userName)
				
			.when()
				.get(Routes.get_url);
			
			return response;
		}
	
	// update user request
	public static Response updateUser(String userName,User payload){
			
			Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username",userName)
				.body(payload)
				
			.when()
				.put(Routes.update_url);
			
			return response;
	}
	
	// delete user request
	public static Response deleteUser(String userName){
		
		Response response = given()
			.pathParam("username", userName)
			
		.when()
			.delete(Routes.delete_url);
		
		return response;
	}
	
}