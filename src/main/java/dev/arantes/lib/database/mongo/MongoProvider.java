package dev.arantes.lib.database.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoProvider {
    private MongoDatabase database;

    public MongoProvider(final String uri, final String databaseName) {
        final MongoClient mongoClient = new MongoClient(new MongoClientURI(uri));
        database = mongoClient.getDatabase(databaseName);
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoCollection<Document> getCollection(final String name) {
        return database.getCollection(name);
    }
}
