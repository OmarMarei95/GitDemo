import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;
import files.Utils;

public class Basics {
	//Add place
	public static void main(String[] args) {
		
		/*
		 * RestAssured.baseURI = "https://rahulshettyacademy.com"; given().log().all()
		 * .queryParam("key", "qaclick123") .header("content-type", "application/json")
		 * .body(Payload.addPlace()).when().post("maps/api/place/add/json")
		 * .then().log().all() .assertThat().statusCode(200) .body("scope",
		 * equalTo("APP")) .header("Server", "Apache/2.4.52 (Ubuntu)");
		 */
		 
		
		
		//Add place -> Update place with new address -> Get place to validate update
		
		  //Add place
		  RestAssured.baseURI = "https://rahulshettyacademy.com";
		  String response = given() .queryParam("key", "qaclick123")
		  .header("content-type","application/json")
		  .body(Payload.addPlace()).when().post("maps/api/place/add/json")
		  .then()
		  .assertThat().statusCode(200) .body("scope", equalTo("APP"))
		  .header("Server", "Apache/2.4.52 (Ubuntu)") .extract().response().asString();
		  
		  System.out.println(response);
		
		  JsonPath js = new JsonPath(response); 
		  String placeID = js.getString("place_id");
		  System.out.println(placeID);
		  
		 
		
		 //Update Place 
		 String newAdress = "70 winter walk, USA"; 
		  
		 given().queryParam("key", "qaclick123")
		 .header("content-type", "application/json")
		 .body("{\r\n"
			 		+ "\"place_id\":\""+placeID+"\",\r\n"
			 		+ "\"address\":\""+newAdress+"\",\r\n"
			 		+ "\"key\":\"qaclick123\"\r\n"
			 		+ "}")
		 .when().put("maps/api/place/update/json")
		 .then().assertThat().statusCode(200)
		 .body("msg", equalTo("Address successfully updated"));
		 
		 //Get Place
		 String getPlaceResponse = given().queryParam("key", "qaclick123")
		 .queryParam("place_id", placeID)
		 .when().get("maps/api/place/get/json")
		 .then().assertThat().statusCode(200)
		 .extract().response().asString();
		 
		 JsonPath json = Utils.rawToJSON(getPlaceResponse);
		 String updatedAddress = json.getString("address");
		 System.out.println(updatedAddress);
		 Assert.assertEquals(newAdress, updatedAddress);
		
	}

}
