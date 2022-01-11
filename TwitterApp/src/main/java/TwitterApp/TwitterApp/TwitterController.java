package TwitterApp.TwitterApp;

import TwitterApp.TwitterApp.Modello.Tweet;
import TwitterApp.TwitterApp.Modello.User;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

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
	innerObject.addProperty("Average_number_of_mentions:", user.mediaMentions());
		
	return innerObject.toString();
	}

	// e' una rotta per 
	@GetMapping("/tweets") 
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
		Gson gson = new Gson();
		ArrayList<Tweet> arrayTweet= new ArrayList<Tweet>();
		for (Tweet t: user.getListTweets() ) {
			
			if (t.getNumOfMentions()>=minMentions) {
				arrayTweet.add(t);
				
			}
		}
		if (arrayTweet.size()==0) {
		return "Non ci sono tweet con " + minMentions + " o piu' menzioni";	
		}
		String jsonInString = gson.toJson(arrayTweet);

		return jsonInString;
	}
}