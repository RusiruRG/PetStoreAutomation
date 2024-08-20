package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker;
	User payload;

	@BeforeClass
	public void setUpData() {
		
		faker = new Faker(); // faker class object
		payload = new User(); // user pojo class object
		
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.internet().emailAddress());
		payload.setPassword(faker.internet().password());
		payload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		Response response = UserEndPoints2.createUser(payload);
		response.then().log().all();
		
		// validation for the status code
		AssertJUnit.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=2)
	public void testGetDataByUserName() {
		
		Response response = UserEndPoints2.readUser(this.payload.getUsername());
		response.then().log().all();
		
		// validations for the status code
		AssertJUnit.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=3)
	public void testUpdateUserByUserName() {
		
		// updated data 
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.internet().emailAddress());
		
		Response response = UserEndPoints2.updateUser(this.payload.getUsername(), payload);
		response.then().log().all();
		// response.then().log().body().statusCode(200)
		
		// validation for the status code
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		
		// checking data after update
		Response responseAfterUpdate = UserEndPoints2.readUser(this.payload.getUsername());
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	
	@Test(priority=4)
	public void testDeleteUserByUserName() {
		
		Response response = UserEndPoints2.deleteUser(this.payload.getUsername());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
}
