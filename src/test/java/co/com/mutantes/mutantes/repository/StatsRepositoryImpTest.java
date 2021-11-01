package co.com.mutantes.mutantes.repository;

import co.com.mutantes.mutantes.model.Stats;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import junit.framework.TestResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatsRepositoryImpTest {
    @InjectMocks
    private  StatsRepositoryImpl statsRepository;
    @Mock
    private MongoDatabase connectMongoDb;
    @Mock
    private MongoCollection mongoCollection;
    @Mock
    private FindIterable tDocuments;
    @Mock
    private Document tResult;

    @Test
    public void getStatsSuccess(){
        Mockito.when(connectMongoDb.getCollection("stats")).thenReturn(mongoCollection);
        Mockito.when(mongoCollection.find()).thenReturn(tDocuments);
        Mockito.when(tDocuments.first()).thenReturn(tResult);
        Mockito.when(tResult.getInteger("count_human_dna")).thenReturn(2);
        Mockito.when(tResult.getInteger("count_mutant_dna")).thenReturn(5);
        Stats  stats = statsRepository.getStats();
        Assert.assertTrue(stats.count_human_dna.equals(2));
        Assert.assertTrue(stats.count_mutant_dna.equals(5));

    }

    @Test
    public void updateSuccess(){
        Mockito.when(connectMongoDb.getCollection("stats")).thenReturn(mongoCollection);
        Mockito.when(mongoCollection.find()).thenReturn(tDocuments);
        Mockito.when(tDocuments.first()).thenReturn(tResult);
        Bson updateValue = new Document("count_mutant_dna", 6);
        statsRepository.updateStats(updateValue);
    }

}
