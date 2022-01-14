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
	    /**
	    * @param id
 		* @param text
 		*/
	    public Tweet(String id, String text) {
	    	
	        this.id = id;
	        this.text = text;

	        this.countMentions();
	    }
	    /**
	    * @return
	    */
	    public String getId() {
	        return id;
	    }
	    /**
	     * @param id
	     */
	    public void setId(String id) {
	        this.id = id;
	    }
	    /**
	     * @return
	     */
	    public String getText() {
	        return text;
	    }
	    /**
	     * @param text
	     */
	    public void setText(String text) {
	        this.text = text;
	    }
	    /**
	     * @return
	     */
	    public Map<String, Integer> getMentions() {
	        return mentions;
	    }
	    // un metodo per inizializzare NumOfMention
	    public void initNumOfMentions(){
	    	this.numOfMentions = this.mentions.values().stream().reduce(0, (acc, el) -> acc + el);
	    }
	    /**
	     * 
	     * @param numOfMentions
	     */
	    public void setNumOfMentions(int numOfMentions) {
			this.numOfMentions = numOfMentions;
		}
	    /**
	     * @return
	     */
		public int getNumOfMentions() {
	        return this.numOfMentions;
	    }
	    public void countMentions() {
	        Map<String, Integer> mentions = new HashMap<String, Integer>();
	        
	        String[] specialChars = {".", ",",":", ";"};

	        for (String word :
	                this.text.split("\\s+")) {
	            if (word.charAt(0) == '@') {
	            	word=word.substring(1);
	            	for (String sp: specialChars) {
	            		word = word.replace(sp, "");
	            	}
	                mentions.put(word, mentions.getOrDefault(word, 0) + 1);
	            }
	        }

	        this.mentions = mentions;
	        initNumOfMentions();
	    }
	    
	     /**
	     * @param mentions -> Map contiene username, Integer numero menzioni
	     */
	    public void setMentions(Map<String, Integer> mentions) {
			this.mentions = mentions;
		}
}

