package TwitterApp.TwitterApp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import TwitterApp.TwitterApp.Modello.Tweet;

@SpringBootTest
class TwitterAppApplicationTests {
/**
 * Testa il numero di menzioni presenti nel tweet
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
	
	@Test 
	void testNumberOfMentions() {
		assertEquals(3,tweetTest.getNumOfMentions());
		
	}
	
	/**
	 * Testa se è stata inserita correttamente la menzione (nome dell'utente e quante volte è stato menzionato)
	 */
	@Test 
	void testMentions() {
		assertTrue(mentionsTest.equals(tweetTest.getMentions()));
	}

}
