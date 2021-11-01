package co.com.mutantes.mutantes.controller;

import co.com.mutantes.mutantes.model.Stats;
import co.com.mutantes.mutantes.service.StatsService;
import co.com.mutantes.mutantes.utils.GeneralUtils;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class StatsControllerTest {
    @InjectMocks
    private StatsController statsController;
    @Mock
    private GeneralUtils generalUtils;
    @Mock
    private StatsService statusService;

    @Test
    public void getStatTest(){
        Stats stats = new Stats();
        stats.count_human_dna = 1;
        stats.count_mutant_dna = 1;
        String message = new Gson().toJson(stats);
        ResponseEntity<Object> response = new ResponseEntity<>(message, HttpStatus.OK);
        Mockito.when(statusService.getStats()).thenReturn(response);
        ResponseEntity<Object> responseExpect =  statsController.getStats();
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("200"));
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("OK"));
        Assert.assertTrue(responseExpect.getBody().toString().contains("1"));

    }
}
