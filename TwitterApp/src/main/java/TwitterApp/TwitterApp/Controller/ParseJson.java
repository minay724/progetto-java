package TwitterApp.TwitterApp.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import TwitterApp.TwitterApp.Modello.Tweet;
import TwitterApp.TwitterApp.Modello.Profilo;

/*Quella classe trasforma gli oggetti json in oggetti java 
 * 
 * */

public class ParseJson {

    public static Profilo profile(String json) {
        Gson gson = new Gson();
        TypeToken<Map<String, Map<String, String>>> typeToken = new TypeToken<>() {};
        Map<String, Map<String, String>> parsedJson = gson.fromJson(json, typeToken.getType());

        Profilo twitterProfile = new Profilo();

        twitterProfile.setId(parsedJson.get("data").get("id"));
        

        return twitterProfile;
    }


    public static List<Tweet> tweets(String json) {

        var parsedJson = JsonParser.parseString(json).getAsJsonObject();

        List<Tweet> tweets = new ArrayList<>();

        parsedJson.getAsJsonArray("data").forEach(jsonTweet -> {
            var parsedJsonTweet = jsonTweet.getAsJsonObject();

            String id = parsedJsonTweet.getAsJsonPrimitive("id").getAsString();
            String text = parsedJsonTweet.getAsJsonPrimitive("text").getAsString();

            Tweet tweet = new Tweet(id, text);

            tweets.add(tweet);
        });

        return tweets;
    }

}

