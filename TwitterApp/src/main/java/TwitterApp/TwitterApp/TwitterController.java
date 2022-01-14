package TwitterApp.TwitterApp;

import TwitterApp.TwitterApp.Modello.Tweet;
import TwitterApp.TwitterApp.Modello.User;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController

public class TwitterController {
	// questo e' un metodo per inizializzare User (classe)
	/** 
	 * @param userName
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private static User initUser(String userName ) throws IOException, URISyntaxException {
		
		User user = new User();
		user.setUserName(userName);
		user.setListTweets();
		 return user;
		 }
	 /**
	 * @param userName
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
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
	/**
	 * @param userName
	 * @param minMentions
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws IllegalArgumentException
	 */
	// e' una rotta per mostrare gli ultimi 100 post di un cliente
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
	/**
	 * @param userName
	 * @param userName2
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws IllegalArgumentException
	 */
	@GetMapping("/mentioned") 
	public String mentioned (@RequestParam(value = "userName") String userName, @RequestParam(value = "userName2" ) String userName2) throws IOException, URISyntaxException,IllegalArgumentException  {
		
		User user;
		try{
			user= initUser(userName);
			
		}catch(IllegalArgumentException error) {
			return "Username not found";
		}
		
		
		 if (user.getListTweets()==null) {
			return "No tweeets found";
			}
		 
		 user.setAllMentioned();
		for (Map.Entry<String, Integer> entry : user.getAllMentioned().entrySet()) {

			String nomeUtente=entry.getKey();
			int numberOfMentions=entry.getValue() ;
			if (nomeUtente.toLowerCase().equals(userName2.toLowerCase())) {
				 
				return userName2+" e' stato menzionato "+ numberOfMentions  + " volte";
			}
			
				
			
		}
		return userName2+" e' stato menzionato 0 volte";
	}
	
	@GetMapping("/mostMentioned")  
	public String mostMentioned(@RequestParam(value = "userName") String userName, @RequestParam(value = "minMentioned" ) int minMentioned) throws IOException, URISyntaxException,IllegalArgumentException  {
		User user;
		try{
			user= initUser(userName);
			
		}catch(IllegalArgumentException error) {
			return "Username not found";
		}
		
		
		 if (user.getListTweets()==null) {
			return "No tweeets found";
			}
		       
		        // enter data into hashmap
		      user.setAllMentioned();
		        Map<String, Integer> hm1 = user.sortByValue(user.getAllMentioned());
		 
		        TreeMap<String, Integer> myNewMap = hm1.entrySet().stream() .limit(minMentioned) .collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

		 Gson gson= new Gson();
		        String jsonInString = gson.toJson(myNewMap);

				return jsonInString;
			
				}
	


}