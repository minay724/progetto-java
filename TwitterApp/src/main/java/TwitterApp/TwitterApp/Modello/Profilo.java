package TwitterApp.TwitterApp.Modello;
/*
* In questa classe abbiamo creato i metodi (get e sit per l' id del profilo )
* in quanto l'id del profilo mi serve per poter fare la chiamata 
* quind faremo prima una richiesta sapendo il username dell'account per creare il suo id e puoi tramite l'id faremo la chiamata per l'ottenimento dei post 
*/

public class Profilo {
	   private String id;
	    private String description;
	    private String createdAt;
	    private String username;
	    private String name;

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(String createdAt) {
	        this.createdAt = createdAt;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	   
	    }
	
