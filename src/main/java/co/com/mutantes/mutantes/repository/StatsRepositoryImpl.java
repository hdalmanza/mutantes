package co.com.mutantes.mutantes.repository;

import co.com.mutantes.mutantes.model.Stats;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatsRepositoryImpl implements StatsRepository{

    @Autowired
    private MongoDatabase connectMongoDb;

    private static final  String STATS = "stats";

    @Override
    public Stats getStats(){
        Stats stats = new Stats();
        Document document= connectMongoDb.getCollection(STATS).find().first();
        stats.count_human_dna = document.getInteger("count_human_dna");
        stats.count_mutant_dna =  document.getInteger("count_mutant_dna");
        return  stats;
    }

    @Override
    public void updateStats(Bson updateValue) {
        Document document= connectMongoDb.getCollection(STATS).find().first();
        Bson updateOperation = new  Document("$set", updateValue);
        connectMongoDb.getCollection(STATS).updateOne(document,updateOperation);
    }

}
