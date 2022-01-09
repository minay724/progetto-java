package TwitterApp.TwitterApp.Modello;


/*
 * In questa classe abbiamo creato i metodi (get e sit per l' id ed il testo del post)
 * */

public class Tweet {

    private String id;
    private String testo;
    
    public Tweet(String id, String testo) {
        this.id = id;
        this.testo = testo;

       // this.contaMenzioni();  e' un metodo ancora non creato 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setText(String testo) {
        this.testo = testo;
    }}

   
    


