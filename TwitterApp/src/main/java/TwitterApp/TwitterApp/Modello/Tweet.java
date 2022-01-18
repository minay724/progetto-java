package TwitterApp.TwitterApp.Modello;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 *In questa classe abbiamo creato i metodi (get e set per l' id ed il testo del post)
 */
public class Tweet {
	    private String id;
	    private String text;
	    private int numOfMentions;
	    private Map<String, Integer> mentions;
	    
	    
	    /**
	     * 
	    * Costruttore tweet che prende:
	    * 1) l'id del post;
	    * 2)il testo del post;
	    * 
 		*/
	    public Tweet() {}
	    /**
	     * @param id l'id dell'account
 		 * @param text il testo del tweet (post)
	     */
	    public Tweet(String id, String text) {
	    	
	        this.id = id;
	        this.text = text;

	        this.countMentions();
	    }
	    /**
	     * 
	    * @return id
	    */
	    public String getId() {
	        return id;
	    }
	    /**
	     * 
	     * @param id id ell'account
	     */
	    public void setId(String id) {
	        this.id = id;
	    }
	    /**
	     * @return text
	     */
	    public String getText() {
	        return text;
	    }
	    /**
	     * 
	     * @param text testo del tweet (post)
	     */
	    public void setText(String text) {
	        this.text = text;
	    }
	    /**
	     * 
	     * @return mentions 
	     */
	    public Map<String, Integer> getMentions() {
	        return mentions;
	    }
	    /**
	     * 
	     *un metodo per inizializzare NumOfMention
	     */
	    public void initNumOfMentions(){
	    	this.numOfMentions = this.mentions.values().stream().reduce(0, (acc, el) -> acc + el);
	    }
	    /**
	     * 
	     * @param numOfMentions numero di menzioni
	     */
	    public void setNumOfMentions(int numOfMentions) {
			this.numOfMentions = numOfMentions;
		}
	    /**
	     * @return numOfMentions
	     */
		public int getNumOfMentions() {
	        return this.numOfMentions;
	    }
		/**
		 *metodo per contare le menzioni  
		 */
	    public void countMentions() {
	        Map<String, Integer> mentions = new HashMap<String, Integer>();
//sono stati inseriti tutti i caratteri "speciali" che potrebbero causare problemi nella ricerca dell'account menzionato
	        String[] specialChars = {".", ",",":", ";","/","\\","_","-"};
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
	     * @param mentions, Map contiene username, Integer numero menzioni
	     */
	    public void setMentions(Map<String, Integer> mentions) {
			this.mentions = mentions;
		}
}