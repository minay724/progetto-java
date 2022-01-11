package TwitterApp.TwitterApp;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import com.google.gson.JsonSyntaxException;

import TwitterApp.TwitterApp.Controller.ParseJson;
import TwitterApp.TwitterApp.Modello.Tweet;
import TwitterApp.TwitterApp.Modello.Profilo;



/* questa classe mi da:
 * 
 * abbiiamo modificato quella classe in modo che permette di ottenere direttamente i post sapendo il username 
 * 
 * */
public class TwitterApi {

	 
	   // dopo aver generato il mio bearer token personale faccio una prova di chiamata 
		  
	private final static String bearerToken  = "AAAAAAAAAAAAAAAAAAAAAL5HWgEAAAAAU2x9Mlm779lmBQsD53bSErOykbI%3DhoAij03NQp0EomeRIRGANc0YkfLppJqP0i5fuJBAy5ANQDHDnp";
		  private final static HttpClient httpClient = HttpClients.custom() // qui abbiamo fatto la configurazione 
		            .setDefaultRequestConfig(RequestConfig.custom()
		                    .setCookieSpec(CookieSpecs.STANDARD).build())
		            .build();

	  // questo metodo chiama la  v2 User Tweet timeline endpoint tramite user id
	   
	

	    public static Profilo getUserProfile(String username) throws IOException, URISyntaxException,JsonSyntaxException {
	        URIBuilder uriBuilder = new URIBuilder(String.format("https://api.twitter.com/2/users/by/username/%s", username));
	        
	      //https://api.twitter.com/2/users/by/username/"nasa"
	        
	        ArrayList<NameValuePair> queryParameters;
	        queryParameters = new ArrayList<>();
	        queryParameters.add(new BasicNameValuePair("user.fields", "pinned_tweet_id"));
	        uriBuilder.addParameters(queryParameters);

	        HttpGet httpGet = new HttpGet(uriBuilder.build());
	        httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
	        httpGet.setHeader("Content-Type", "application/json");

	        HttpResponse response = httpClient.execute(httpGet);
	        HttpEntity entity = response.getEntity();
	        if(response.getStatusLine().getStatusCode() !=200) {
	        	return null;
	        }        

	        if (entity != null) {
	            String jsonResponse = EntityUtils.toString(entity, "UTF-8");
	         
	          try {
	        	  return ParseJson.profile(jsonResponse);
	          }catch (JsonSyntaxException error) {
	        	  return null;
	          }
	          
	        }

	        return null;
	    }


	    public static List<Tweet> getTweets(String username) throws IOException, URISyntaxException {
	       Profilo twitterProfile = getUserProfile(username);

	       if (twitterProfile == null) {
	            throw new IllegalArgumentException("Username nooooot found");
	        }
	       

	        String userId = Objects.requireNonNull(getUserProfile(username)).getId();

	        HttpClient httpClient = HttpClients.custom()
	                .setDefaultRequestConfig(RequestConfig.custom()
	                        .setCookieSpec(CookieSpecs.STANDARD).build())
	                .build();

	        URIBuilder uriBuilder = new URIBuilder(String.format("https://api.twitter.com/2/users/%s/tweets", userId));
	        ArrayList<NameValuePair> queryParameters;
	        queryParameters = new ArrayList<>();
<<<<<<< HEAD
	       
	        queryParameters.add(new BasicNameValuePair( "max_results", "100")); // qui come prova abbiamo messo soltanto 10 post
=======
	        queryParameters.add(new BasicNameValuePair("tweet.fields", "created_at"));
	        queryParameters.add(new BasicNameValuePair( "max_results", "10")); // qui come prova abbiamo messo soltanto 10 post
>>>>>>> 0619e274660a9a978391b6debe851b689630e584

	        
	        uriBuilder.addParameters(queryParameters);

	        HttpGet httpGet = new HttpGet(uriBuilder.build());
	        httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
	        httpGet.setHeader("Content-Type", "application/json");

	        HttpResponse response = httpClient.execute(httpGet);
	        HttpEntity entity = response.getEntity();

	        if (entity != null) {
	            String json = EntityUtils.toString(entity, "UTF-8");
	            System.out.println(json);
	      
	            return ParseJson.tweets(json);
	        }

	        return null;
	    }
	}