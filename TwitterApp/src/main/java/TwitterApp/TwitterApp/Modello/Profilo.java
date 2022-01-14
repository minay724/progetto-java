package TwitterApp.TwitterApp.Modello;
/*
* In questa classe abbiamo creato i metodi (get e sit per l' id del profilo )
* in quanto l'id del profilo mi serve per poter fare la chiamata 
* quind faremo prima una richiesta sapendo il username dell'account per creare il suo id e puoi tramite l'id faremo la chiamata per l'ottenimento dei post 
*/

public class Profilo {
	   private String id;
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
}

 
	    
	
