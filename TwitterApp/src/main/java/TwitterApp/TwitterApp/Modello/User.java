package TwitterApp.TwitterApp.Modello;
import java.net.URISyntaxException;
import java.io.IOException;
import TwitterApp.TwitterApp.TwitterApi;

import java.util.List;

public class User extends Profilo {

	
<<<<<<< HEAD
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
=======
		
		private List<Tweet> listTweets;
		private String userName;
>>>>>>> 0619e274660a9a978391b6debe851b689630e584
		
		
<<<<<<< HEAD
	}
	public float mediaMentions() {
		if (this.tweetsCount!=0) {
		return (float) totNumOfMentions() / this.tweetsCount; // qua abbiamo fatto il casting perche' non sono float
		}
		else {
			return 0;
			
		}
	}
	
}

/*String mostMentionedUser = null;
        int totalNumberOfMentions = 0;

        for (var mentionedUsername :
                allMentions.keySet()) {
            if (allMentions.get(mentionedUsername) > totalNumberOfMentions) {
                totalNumberOfMentions = allMentions.get(mentionedUsername);
                mostMentionedUser = mentionedUsername;
            }
        }*/
	
=======
		public List<Tweet> getListTweets() {
			return listTweets;
		}
		public void setListTweets() throws IOException, URISyntaxException {
			this.listTweets = TwitterApi.getTweets(this.userName);
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		public int totNumOfMentions() {
			int somma =0;
			for (Tweet t : this.listTweets) {
				t.countMentions();
				somma+=t.getNumOfMentions();
				
			     }
			return somma;
			
		}
	}
		
		


>>>>>>> 0619e274660a9a978391b6debe851b689630e584
