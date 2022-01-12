package TwitterApp.TwitterApp.Modello;
import java.net.URISyntaxException;
import java.io.IOException;
import TwitterApp.TwitterApp.TwitterApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {


	private List<Tweet> listTweets;
	private String userName;
	private int tweetsCount = 0;
	Map<String, Integer> allMentioned = new HashMap<String, Integer>();//
	 //private String mostMentionedUser = null; // inizializzato con 0
	
	public int getTweetsCount() {
		return tweetsCount;
	}
	public void setTweetsCount() {
		if (listTweets!=null) {
			
			this.tweetsCount = listTweets.size();
			}
		else {
			 this.tweetsCount =0;
		}
	}
	public List<Tweet> getListTweets() {
		return listTweets;
	}
	public void setListTweets() throws IOException, URISyntaxException {
		this.listTweets = TwitterApi.getTweets(this.userName);
	
		setTweetsCount();

	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int totNumOfMentions() {
		if (this.listTweets == null) {
			return 0;
		}
		
		int somma =0;
		for (Tweet t : this.listTweets) {
			t.countMentions();
			somma+=t.getNumOfMentions();
			
		     }
		return somma;
		
	}
	public float mediaMentions() {
		if (this.tweetsCount!=0) {
		return (float) totNumOfMentions() / this.tweetsCount;
		}
		else {
			return 0;
			
		}
	}
/*
 * quel metodo conta quante volte  e' stato menzionato un utente negli ultimi 100 tweet 
 * 
 * */
	public void setAllMentioned() {
		for (Tweet t : this.listTweets) {
			for (Map.Entry<String, Integer> entry : t.getMentions().entrySet()) {
				String nu=entry.getKey();
				int numberOfMentions=entry.getValue();
				allMentioned.put(nu, allMentioned.getOrDefault(nu, 0) + numberOfMentions);
		    }
		}
     }
	
	public Map<String, Integer> getAllMentioned(){
		return	this.allMentioned;
	}
}

/*
		 * allMentions.put(mentionedUsername,
		 * allMentions.getOrDefault(mentionedUsername, 0) + 1);
		 * 
		 * int totalNumberOfMentions = 0; for (var mentionedUsername :
		 * allMentions.keySet()) { if (allMentions.get(mentionedUsername) >
		 * totalNumberOfMentions) { totalNumberOfMentions =
		 * allMentions.get(mentionedUsername); mostMentionedUser = mentionedUsername; }
		 * 
		 * 
		 * }
		 */

