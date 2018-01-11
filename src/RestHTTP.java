

import static io.restassured.RestAssured.given;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestHTTP {
	static Response response ;
	public static Response DoGet() throws Exception {
		
		RestAssured.basePath = "/api/ticker";
		RestAssured.baseURI  = "https://koinex.in";
		response = 
		(Response) given().
	    contentType("application/json").
	    when().
        get();
		
		return response;
	}
	
	

}
