import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import files.Utils;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	@Test
	@org.junit.Test
	public void sumOfCourses() {
		JsonPath js = Utils.rawToJSON(Payload.coursePrice());
		int courseCount = js.getInt("courses.size()");
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		int totalPrice = 0;
		for(int i=0; i<courseCount; i++) {
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int coursePrice = copies*price;
			totalPrice = totalPrice + coursePrice;
		}
		Assert.assertEquals(totalPrice, purchaseAmount);
		
	}

}
