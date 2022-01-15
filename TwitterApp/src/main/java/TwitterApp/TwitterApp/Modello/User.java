package TwitterApp.TwitterApp.Modello;
import java.net.URISyntaxException;
import java.io.IOException;
import TwitterApp.TwitterApp.TwitterApi;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class User {

	private List<Tweet> listTweets;
	private String userName;
	private int tweetsCount = 0;
	private HashMap<String, Integer> allMentioned = new HashMap<String, Integer>();//
	 
	/**
	 * @return tweetsCount
	 */
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
	/** 
	 * @return listTweets
	 */
	public List<Tweet> getListTweets() {
		return listTweets;
	}
	/**
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void setListTweets() throws IOException, URISyntaxException {
		this.listTweets = TwitterApi.getTweets(this.userName);
	
		setTweetsCount();
	}
	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * è un metodo per calcolare il numero totale di menzioni
	 * @return somma, ritorna numero totale di menzioni
	 */
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
	/** 
	 * è un metodo per calcolare il numero medio dei menzioni  
	 * @return totNumOfMentions() / this.tweetsCount
	 */
	public float mediaMentions() {
		if (this.tweetsCount!=0) {
		return (float) totNumOfMentions() / this.tweetsCount;
		}
		else {
			return 0;
		}
	}
	/**
	 * quel metodo conta quante volte  e' stato menzionato un utente negli ultimi 100 tweet 
	 */
	public void setAllMentioned() {
		for (Tweet t : this.listTweets) {
			for (Map.Entry<String, Integer> entry : t.getMentions().entrySet()) {
				String nu=entry.getKey();
				int numberOfMentions=entry.getValue();
				allMentioned.put(nu, allMentioned.getOrDefault(nu, 0) + numberOfMentions);
		    }
		}
     }
	public HashMap<String, Integer> getAllMentioned(){
		
	return this.allMentioned;	
	}
	/**
	 * metodo per ordinare (per valore) l'hashmap allMentioned
	 * @param map, allMentioned
	 * @return
	 */
    public  HashMap<String, Integer>
    sortByValue(Map<String, Integer> map)
    {
    	// Crezione di una lista dagli elementi di Hashmap
        List<Map.Entry<String, Integer> > list
            = new LinkedList<Map.Entry<String, Integer> >(
                map.entrySet());
        //ordinamento lista usando l' "espressione lambda"
        Collections.sort(
            list,
            (i1,
             i2) -> i2.getValue().compareTo(i1.getValue()));
        //inserimento degli elementi ordinati nell' hashmap
        HashMap<String, Integer> temp
            = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
        	
        		temp.put(aa.getKey(), aa.getValue());
        		
            
        }
        return temp;
}
    }



