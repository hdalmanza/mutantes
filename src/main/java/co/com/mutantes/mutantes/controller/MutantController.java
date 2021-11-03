package co.com.mutantes.mutantes.controller;

import co.com.mutantes.mutantes.model.Dna;
import co.com.mutantes.mutantes.service.MutantService;
import co.com.mutantes.mutantes.utils.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {

    @Autowired
    private MutantService mutantService;
    @Autowired
    private GeneralUtils generalUtils;



    @PostMapping(value = "mutant", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> isMutant(@RequestBody(required = true) @Validated Dna dna){
        ResponseEntity<Object> isMutant = null;
        try {
            isMutant =mutantService.isMutant(dna.dna);
        }catch (Exception exception){
            new ResponseEntity<>( generalUtils.buildMessage("Error" + exception.getMessage()),
                    HttpStatus.FORBIDDEN);
        }
        return isMutant;
    }
}
