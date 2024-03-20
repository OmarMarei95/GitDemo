import org.junit.Test;
import org.testng.annotations.DataProvider;

import files.Payload;
import files.Utils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@org.testng.annotations.Test(dataProvider = "BooksData")
	@Test
	public void addBook(String isbn, String isle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("content-type","application/json")
		.body(Payload.addBook(isbn,isle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = Utils.rawToJSON(response);
		String id = js.get("ID");
		System.out.println(id);
	}
	
	@DataProvider(name = "BooksData")
	public Object[][] getData(){
		return new Object[][] {{"qwe","123"},{"asd","456"},{"zxc","789"}};
	}
System.out.println("Git Trial");
System.out.println("Testing Git integration between two projects");
}
