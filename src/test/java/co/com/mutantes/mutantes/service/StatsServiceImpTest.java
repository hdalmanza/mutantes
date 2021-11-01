package co.com.mutantes.mutantes.service;

import co.com.mutantes.mutantes.model.Stats;
import co.com.mutantes.mutantes.repository.StatsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class StatsServiceImpTest {
    @InjectMocks
    private StatsServiceImpl statsServiceImpl;
    @Mock
    private StatsRepository statsRepository;

    @Test
    public void getStatsIsEmpty(){
        Stats stats = new Stats();
        Mockito.when(statsRepository.getStats()).thenReturn(stats);
        ResponseEntity<Object> response =  statsServiceImpl.getStats();
        Assert.assertTrue(response.getStatusCode().toString().contains("200"));
        Assert.assertTrue(response.getStatusCode().toString().contains("OK"));
        Assert.assertTrue(response.getBody().toString().contains("0.0"));

    }

    @Test
    public void getStatsIsNotEmpty(){
        Stats stats = new Stats();
        stats.count_mutant_dna = 1;
        stats.count_human_dna = 1;
        Mockito.when(statsRepository.getStats()).thenReturn(stats);
        ResponseEntity<Object> response =  statsServiceImpl.getStats();
        Assert.assertTrue(response.getStatusCode().toString().contains("200"));
        Assert.assertTrue(response.getStatusCode().toString().contains("OK"));
        Assert.assertTrue(response.getBody().toString().contains("1"));

    }
}
