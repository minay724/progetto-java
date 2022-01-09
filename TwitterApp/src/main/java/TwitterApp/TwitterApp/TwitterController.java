package TwitterApp.TwitterApp;

import TwitterApp.TwitterApp.Modello.User;
import java.io.IOException;
import java.net.URISyntaxException;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
//import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController

public class TwitterController {
	// questo e' un metodo per inizializzare User (classe)
	
	private static User initUser(String userName ) throws IOException, URISyntaxException {
		
		User user = new User();
		user.setUserName(userName);
		user.setListTweets();
		 return user;
		 }

	
	@GetMapping("/stats") 
public String stats (@RequestParam(value = "userName") String userName) throws IOException, URISyntaxException {
	User user = initUser(userName);
	
	
	 JsonObject innerObject = new JsonObject();
	 
	innerObject.addProperty("Total_number_of_mentions:", user.totNumOfMentions());
	return innerObject.toString();
	}

	
	@GetMapping("/tweets") 
	public String tweets (@RequestParam(value = "userName") String userName) throws IOException, URISyntaxException  {
		User user = initUser(userName);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(user.getListTweets());

		return jsonInString;
	}
}