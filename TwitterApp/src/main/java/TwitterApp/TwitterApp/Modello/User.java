package TwitterApp.TwitterApp.Modello;
import java.net.URISyntaxException;
import java.io.IOException;
import TwitterApp.TwitterApp.TwitterApi;

import java.util.List;

public class User {

	
		
		private List<Tweet> listTweets;
		private String userName;
		
		
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
		
		


