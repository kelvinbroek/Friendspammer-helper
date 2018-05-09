package nl.hu.sie.bep.friendspammer;

import domain.MailDTO;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MongoSaver {

	static final Logger logger = LoggerFactory.getLogger(MongoSaver.class);

	public static boolean saveEmail(String to, String from, String subject, String text, Boolean html) {
		String userName = "shotair";
		String password = "FriendSpamPass";
		String database = "friendspammer";
		
		MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
		
		boolean success = true;
		
		try (MongoClient mongoClient = new MongoClient(new ServerAddress("MongoDB", 27939), credential, MongoClientOptions.builder().build()) ) {
			
			MongoDatabase db = mongoClient.getDatabase( database );
			
			MongoCollection<Document> c = db.getCollection("email");
			
			Document  doc = new Document ("to", to)
			        .append("from", from)
			        .append("subject", subject)
			        .append("text", text)
			        .append("asHtml", html);
			c.insertOne(doc);
		} catch (MongoException mongoException) {
			logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE SAVING TO MONGO XXXXXXXXXXXXXXXXXXXXXXXXXX");
			logger.error(mongoException.getMessage());
			success = false;
		}
		
		return success;
 		
	}

	public List<MailDTO> getHistory() {
		ArrayList<MailDTO> result = new ArrayList<>();

		String userName = "shotair";
		String password = "FriendSpamPass";
		String database = "friendspammer";

		MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("MongoDB", 27939), credential, MongoClientOptions.builder().build());
		MongoDatabase db = mongoClient.getDatabase( database );
		MongoCollection<Document> c = db.getCollection("email");
		Iterator<Document> it = c.find().iterator();

		while(it.hasNext()) {
			Document email = it.next();
			result.add(new MailDTO(	"" + email.get("to"),"" +  email.get("from"), "" + email.get("subject"), "" + email.get("text"), "" + email.get("asHtml")));
		}
		mongoClient.close();
		return result;
	}
	
	public static void main(String ...args) {
		logger.info("test");
	}
}
