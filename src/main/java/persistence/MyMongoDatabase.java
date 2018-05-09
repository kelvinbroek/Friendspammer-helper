package persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MyMongoDatabase {

    public static com.mongodb.client.MongoDatabase getDatabase() {
        String userName = "shotair";
        String password = "FriendSpamPass";
        String database = "friendspammer";

        MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("MongoDB", 27939), credential, MongoClientOptions.builder().build());
        com.mongodb.client.MongoDatabase db = mongoClient.getDatabase( database );
        mongoClient.close();

        return db;
    }
}
