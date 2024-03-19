import org.testng.Assert;

import files.Payload;
import files.Utils;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		
		JsonPath js = Utils.rawToJSON(Payload.coursePrice());
		int courseCount = js.getInt("courses.size()");
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		String courseTitle = js.getString("courses[0].title");
		System.out.println(courseCount+"___"+purchaseAmount+"___"+courseTitle);
		
		int totalPrice = 0;
		for(int i=0; i<courseCount; i++) {
			String title = js.getString("courses["+i+"].title");
			int price = js.getInt("courses["+i+"].price");
			System.out.println("Course title: "+title+"  Course price: "+price);
			int copies = js.getInt("courses["+i+"].copies");
			int coursePrice = copies*price;
			totalPrice = totalPrice + coursePrice;
		}
		Assert.assertEquals(totalPrice, purchaseAmount);
		
		for(int i=0; i<courseCount; i++) {
			String title = js.getString("courses["+i+"].title");
			if(title.equals("RPA")) {
				int numberOfCopies = js.getInt("courses["+i+"].copies");
				System.out.println(numberOfCopies);
				break;
			}
		}
		

	}

}
