package TwitterApp.TwitterApp;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/* questa classe mi da:
 * 
 * 1) la data in cui e' stato creato il post 
 * 2)l'id del post
 * 3) il testo del post
 * tutto informato json (nella console )
 * 
 * */
public class TwitterApi {

	  public static void main(String args[]) throws IOException, URISyntaxException {
	   // dopo aver generato il mio bearer token personale faccio una prova di chiamata 
		  
		  final String bearerToken = "AAAAAAAAAAAAAAAAAAAAAL5HWgEAAAAAU2x9Mlm779lmBQsD53bSErOykbI%3DhoAij03NQp0EomeRIRGANc0YkfLppJqP0i5fuJBAy5ANQDHDnp";
	    // come prova ho messo l'id (generato tramite un link ) dell'UNIVPM
	    	// https://tweeterid.com/   il link per generare l'id di un acount Twitter
	      String response = getTweets("1304170778", bearerToken);
	      System.out.println(response);
	  }

	  // questo metodo chiama la  v2 User Tweet timeline endpoint tramite user id
	   
	  private static String getTweets(String userId, String bearerToken) throws IOException, URISyntaxException {
	    String tweetResponse = null;

	    HttpClient httpCliente = HttpClients.custom()
	        .setDefaultRequestConfig(RequestConfig.custom()
	            .setCookieSpec(CookieSpecs.STANDARD).build())
	        .build();

	    URIBuilder uriBuilder = new URIBuilder(String.format("https://api.twitter.com/2/users/%s/tweets", userId));
	    ArrayList<NameValuePair> parametri;
	   parametri = new ArrayList<>();
	   parametri.add(new BasicNameValuePair("tweet.fields", "created_at"));
	    uriBuilder.addParameters(parametri);

	    HttpGet httpGet = new HttpGet(uriBuilder.build());
	    httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
	    httpGet.setHeader("Content-Type", "application/json");

	    HttpResponse response = httpCliente.execute(httpGet);
	    HttpEntity entity = response.getEntity();
	    if (null != entity) {
	      tweetResponse = EntityUtils.toString(entity, "UTF-8");
	    }
	    return tweetResponse;
	  }
	}