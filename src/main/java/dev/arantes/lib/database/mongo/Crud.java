package dev.arantes.lib.database.mongo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Crud {
    private final MongoCollection<Document> collection;

    public Crud(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    // Create operations
    public void create(Document doc) {
        collection.insertOne(doc);
    }

    // Search operations
    public boolean exists(Document doc) {
        return collection.find(doc).first() != null;
    }

    public Document findOne(Document doc) {
        return collection.find(doc).first();
    }

    // Delete operations
    public Document findAndDelete(Document doc) {
        return collection.findOneAndDelete(doc);
    }

    public void delete(final Document doc) {
        collection.deleteOne(doc);
    }

    // Update operations
    public Document findAndUpdate(final Document who, final Document doc) {
        return collection.findOneAndUpdate(who, new Document("$set", doc));
    }

    public void update(final Document who, final Document doc) {
        collection.updateOne(who, new Document("$set", doc));
    }

    // Others
    public MongoCollection<Document> getCollection() {
        return collection;
    }
}
