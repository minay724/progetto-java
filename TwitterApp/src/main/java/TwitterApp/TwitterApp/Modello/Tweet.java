package TwitterApp.TwitterApp.Modello;

import java.util.HashMap;
import java.util.Map;

/*
 * In questa classe abbiamo creato i metodi (get e sit per l' id ed il testo del post)
 * */

public class Tweet {
	  private String id;
	    private String text;
	    private int numOfMentions;
	    
	    private Map<String, Integer> mentions;

	    public Tweet(String id, String text) {
	        this.id = id;
	        this.text = text;

	        this.countMentions();
	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getText() {
	        return text;
	    }

	    public void setText(String text) {
	        this.text = text;
	    }

	    public Map<String, Integer> getMentions() {
	        return mentions;
	    }
	    
	    public void initNumOfMentions(){
	    	this.numOfMentions = this.mentions.values().stream().reduce(0, (acc, el) -> acc + el);
	    }
	    
	    public void setNumOfMentions(int numOfMentions) {
			this.numOfMentions = numOfMentions;
		}

		public int getNumOfMentions() {
	        return this.numOfMentions;
	    }

	    public void countMentions() {
	        Map<String, Integer> mentions = new HashMap<String, Integer>();

	        for (var word :
	                this.text.split("\\s+")) {
	            if (word.charAt(0) == '@') {
	                mentions.put(word, mentions.getOrDefault(word, 0) + 1);
	            }
	        }

	        this.mentions = mentions;
	        initNumOfMentions();
	    }
	    

	    public void setMentions(Map<String, Integer> mentions) {
			this.mentions = mentions;
		}}

