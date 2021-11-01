package co.com.mutantes.mutantes.config;

import com.mongodb.client.MongoDatabase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConfigMutantTest {
    @InjectMocks
    private ConfigMutant configMutant;

    @Test
    public void createConnectionTest(){
        MongoDatabase dbExpect = configMutant.connectMongoDb();
        Assert.assertTrue(dbExpect.getName().equals("mutant"));
    }
}
