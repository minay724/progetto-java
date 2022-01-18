package TwitterApp.TwitterApp;
import TwitterApp.TwitterApp.Modello.Tweet;
import TwitterApp.TwitterApp.Modello.User;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
/**
 * 
 *Questa e' la classe controller che contiene le rotte 
 */
@RestController
public class TwitterController {
	
	/**
	 * questo e' un metodo per inizializzare User (classe)
	 * @param userName
	 * @return user
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
	 * è una rotta di tipo get che calcola il numero totale e il numero medio di menzioni 
	 * @param userName
	 * @return oggettoJson
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
	
	 JsonObject oggettoJson = new JsonObject();
	 
	oggettoJson.addProperty("Total_number_of_mentions:", user.totNumOfMentions());
	oggettoJson.addProperty("Average_number_of_mentions:", user.mediaMentions());
		
	return oggettoJson.toString();
	}

	
	/**
	 * e' una rotta di tipo get che trova gli ultimi 100 post di un account
	 * @param userName
	 * @param minMentions, numero minimo di menzioni presenti in un post da cercare
	 * @return jsonInString, array di tweets (post)
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws IllegalArgumentException
	 */
	@GetMapping("/tweets") 
	public String tweets (@RequestParam(value = "userName") String userName, @RequestParam(value = "minMentions", defaultValue = "0" ) String minMentions) throws IOException, URISyntaxException,IllegalArgumentException  {
		
		Integer min_mentions=null;
		try {
		min_mentions=Integer.valueOf(minMentions);
		
		}catch(Exception e) {
			return "minMentions deve essere un numero! ";		
		}		
		
		if (min_mentions <0) {
			return "minMentions deve essere un numero positivo";
		}
		
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
			
			if (t.getNumOfMentions()>=min_mentions) {
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
	 * 
	 * @param userName, nome dell'account
	 * @param userName2, nome dell'account che si vuole cercare quante volte è stato menzionato da userName
	 * @return userName2, ritorna la stringa userName2 + quante volte è stato menzionato 
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
	/**
	 * 
	 * @param userName
	 * @param numOfMostMentioned, numero di account più menzionati da visualizzare
	 * @return jsonInString
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws IllegalArgumentException
	 */
	@GetMapping("/mostMentioned")  
	public String mostMentioned(@RequestParam(value = "userName") String userName, @RequestParam(value = "numOfMostMentioned" ) String numOfMostMentioned) throws IOException, URISyntaxException,IllegalArgumentException 
	{
		
		Integer num_of_most_mentioned=null;
		try {
			num_of_most_mentioned=Integer.valueOf(numOfMostMentioned);
		
		}catch(Exception e) {
			return "numOfMostMentioned deve essere un numero! ";		
		}		
		
		if (num_of_most_mentioned <0) {
			return "numOfMostMentioned deve essere un numero positivo";
		}
		
		User user;
		try{
			user= initUser(userName);
			
		}catch(IllegalArgumentException error) {
			return "Username not found";
		}
		
		
		 if (user.getListTweets()==null) {
			return "No tweeets found";
			}
		    //riempimento hashmap (AllMentioned)
		      user.setAllMentioned();
		      
		        HashMap<String, Integer> allMentionedNew = user.sortByValue(user.getAllMentioned());
		        int i = 0;
		        HashMap<String, Integer> allMentionedCut = new LinkedHashMap<String, Integer>();
	        for (Map.Entry<String, Integer> aa : allMentionedNew.entrySet()) {
	        		if (i < num_of_most_mentioned) {
	        			allMentionedCut.put(aa.getKey(), aa.getValue());
	        		}
	        		i++;
	            
	        }
		 
		 Gson gson= new Gson();
		        String jsonInString = gson.toJson(allMentionedCut);

				return jsonInString;
			
	}

}