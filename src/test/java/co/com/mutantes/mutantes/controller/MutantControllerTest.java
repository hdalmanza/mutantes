package co.com.mutantes.mutantes.controller;

import co.com.mutantes.mutantes.model.Dna;
import co.com.mutantes.mutantes.utils.GeneralUtils;
import net.bytebuddy.matcher.ElementMatchers;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MutantControllerTest {
    @InjectMocks
    private  MutantController mutantController;
    @Mock
    private co.com.mutantes.mutantes.service.mutantService mutantService;
    @Mock
    private GeneralUtils generalUtils;

    @Test
    public void isMutant(){
        Dna dna = new Dna();
        List<String> list =  Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        dna.dna = list;
        ResponseEntity<Object> response = new ResponseEntity<>(generalUtils.buildMessage("true"), HttpStatus.FORBIDDEN);
        Mockito.when(mutantService.isMutant(dna.dna)).thenReturn(response);
        ResponseEntity<Object> responseExpect = mutantController.isMutant(dna);
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("403"));
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("FORBIDDEN"));
    }

    @Test
    public void isHuman(){
        Dna dna = new Dna();
        List<String> list =  Arrays.asList("GTGCGA","CTGTGC","ATATGT","AGGAGG","CCCTTA","TCACTG");
        dna.dna = list;
        ResponseEntity<Object> response = new ResponseEntity<>(generalUtils.buildMessage("false"), HttpStatus.OK);
        Mockito.when(mutantService.isMutant(dna.dna)).thenReturn(response);
        ResponseEntity<Object> responseExpect = mutantController.isMutant(dna);
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("200"));
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("OK"));
    }

}
