package files;

import io.restassured.path.json.JsonPath;

public class Utils {
	public static JsonPath rawToJSON(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	}
}
