package co.com.mutantes.mutantes.repository;

import co.com.mutantes.mutantes.model.Stats;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatsRepositoryImpl implements StatsRepository{

    @Autowired
    private MongoDatabase connectMongoDb;

    @Override
    public Stats getStats(){
        Stats stats = new Stats();
        Document document= connectMongoDb.getCollection("stats").find().first();
        stats.count_human_dna = document.getInteger("count_human_dna");
        stats.count_mutant_dna =  document.getInteger("count_mutant_dna");
        return  stats;
    }

    @Override
    public void updateStats(Bson updateValue) {
        MongoCollection mongoCollection = connectMongoDb.getCollection("stats");
        Document document= connectMongoDb.getCollection("stats").find().first();
        Bson updateOperation = new  Document("$set", updateValue);
        mongoCollection.updateOne(document,updateOperation);
    }

}
