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
		User user;
		try{
			user= initUser(userName);
		}catch(IllegalArgumentException error) {
			return "Username not found";
		}
	
	 JsonObject innerObject = new JsonObject();
	 
	innerObject.addProperty("Total_number_of_mentions:", user.totNumOfMentions());
	return innerObject.toString();
	}

	
	@GetMapping("/tweets") 
<<<<<<< HEAD
	public String tweets (@RequestParam(value = "userName") String userName, @RequestParam(value = "minMentions", defaultValue = "0" ) int minMentions) throws IOException, URISyntaxException,IllegalArgumentException  {
		
		User user;
		try{
			user= initUser(userName);
		}catch(IllegalArgumentException error) {
			return "Username not found";
		}
		
		
		 if (user.getListTweets()==null) {
			return "No tweeets found";
			}
=======
	public String tweets (@RequestParam(value = "userName") String userName) throws IOException, URISyntaxException  {
		User user = initUser(userName);
>>>>>>> 0619e274660a9a978391b6debe851b689630e584
		Gson gson = new Gson();
		String jsonInString = gson.toJson(user.getListTweets());

		return jsonInString;
	}
}