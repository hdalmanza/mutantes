package co.com.mutantes.mutantes.service;

import co.com.mutantes.mutantes.model.Stats;
import co.com.mutantes.mutantes.repository.StatsRepository;
import co.com.mutantes.mutantes.utils.GeneralUtils;
import co.com.mutantes.mutantes.utils.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class mutantServiceImplTest {
    @InjectMocks
    private MutantServiceImpl mutantServiceImpl;
    @Mock
    private Parameters parameters;
    @Mock
    private GeneralUtils generalUtils;
    @Mock
    private StatsRepository statsRepository;

    @Before
    public void init(){
        parameters.sizeSequence = 4;
        parameters.listSequenceMutant = Arrays.asList("AAAA","TTTT","CCCC","GGGG");
        parameters.regex = "^[ATCG]+$";
    }

    @Test
    public void isMutantTest(){
        List<String> dna =  Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        Stats stats = new Stats();
        Mockito.when(statsRepository.getStats()).thenReturn(stats);
        ResponseEntity<Object> response = mutantServiceImpl.isMutant(dna);
        Assert.assertTrue(response.getStatusCode().toString().contains("403"));
        Assert.assertTrue(response.getStatusCode().toString().contains("FORBIDDEN"));
    }
    @Test
    public void isMutantAndStatsIsNotEmptyTest(){
        List<String> dna =  Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        Stats stats = new Stats();
        stats.count_mutant_dna = 1;
        Mockito.when(statsRepository.getStats()).thenReturn(stats);
        ResponseEntity<Object> response = mutantServiceImpl.isMutant(dna);
        Assert.assertTrue(response.getStatusCode().toString().contains("403"));
        Assert.assertTrue(response.getStatusCode().toString().contains("FORBIDDEN"));
    }



    @Test
    public void isHumanTest(){
        List<String> dna =  Arrays.asList("GTGCGA","CTGTGC","ATATGT","AGGAGG","CCCTTA","TCACTG");
        Stats stats = new Stats();
        Mockito.when(statsRepository.getStats()).thenReturn(stats);
        ResponseEntity<Object> response = mutantServiceImpl.isMutant(dna);
        Assert.assertTrue(response.getStatusCode().toString().contains("200"));
        Assert.assertTrue(response.getStatusCode().toString().contains("OK"));
    }
    @Test
    public void isHumanAndStatsIsNotEmptyTest(){
        List<String> dna =  Arrays.asList("GTGCGA","CTGTGC","ATATGT","AGGAGG","CCCTTA","TCACTG");
        Stats stats = new Stats();
        stats.count_mutant_dna = 1;
        Mockito.when(statsRepository.getStats()).thenReturn(stats);
        ResponseEntity<Object> response = mutantServiceImpl.isMutant(dna);
        Assert.assertTrue(response.getStatusCode().toString().contains("200"));
        Assert.assertTrue(response.getStatusCode().toString().contains("OK"));
    }
}
