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
	private Map<String, Integer> allMentioned = new HashMap<String, Integer>();//
	 
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
	 * @return
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
	 * e' un metodo per calcolare il numero totale di menzioni
	 * @return somma
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
	/** e' un metodo per calcolare il numero medio dei menzioni  
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
/*
 * quel metodo conta quante volte  e' stato menzionato un utente negli ultimi 100 tweet 
 * 
 * 
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
	/**
	 * @return allMentioned
	 */
	public Map<String, Integer> getAllMentioned(){
		return	this.allMentioned;
	}
	// e' un metodo per ordinare hashmap per valore 
    public  HashMap<String, Integer>
    sortByValue(Map<String, Integer> map)
    {
        // creazione di una lista 
        List<Map.Entry<String, Integer> > list
            = new LinkedList<Map.Entry<String, Integer> >(
                map.entrySet());
 
        // ordinamento 
        Collections.sort(
            list,
            (i1,
             i2) -> i2.getValue().compareTo(i1.getValue()));
 
        // inserimento della data dalla lista al hashmap
        HashMap<String, Integer> temp
        = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
        	
        		temp.put(aa.getKey(), aa.getValue());
        		
            
        }
        return temp;
    }
}



