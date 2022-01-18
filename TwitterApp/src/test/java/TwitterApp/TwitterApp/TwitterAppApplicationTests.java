package TwitterApp.TwitterApp;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import TwitterApp.TwitterApp.Modello.Tweet;
@SpringBootTest
class TwitterAppApplicationTests {
/**
 * da tweet testiamo 
 */
	Tweet tweetTest = new Tweet();
	Map<String, Integer> mentionsTest = new HashMap<String, Integer>();
	
	@BeforeEach
	void contextLoads() {
		tweetTest.setText("@fcBarca @messi @fcBarca ciao");
		tweetTest.countMentions();
		
		mentionsTest.put("fcBarca", 2);
		mentionsTest.put("messi", 1);
	}
}