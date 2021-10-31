package co.com.mutantes.mutantes.service;

import co.com.mutantes.mutantes.model.Stats;
import co.com.mutantes.mutantes.repository.StatsRepository;
import co.com.mutantes.mutantes.utils.GeneralUtils;
import co.com.mutantes.mutantes.utils.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class mutantServiceImp implements mutantService{

    private List<String> letters;
    @Autowired
    private Parameters parameters;
    @Autowired
    private GeneralUtils generalUtils;
    @Autowired
    private StatsRepository statsRepository;

    @Override
    public ResponseEntity<Object> isMutant(List<String> dna){
        ResponseEntity<Object> response = null;
        for (String data:dna) {
          if( isValidSequence(data)){
              response =  new ResponseEntity<>(generalUtils.buildMessage("false"), HttpStatus.OK);
          }else{
              return new ResponseEntity<>(generalUtils.buildMessage(parameters.sequenceInvalid + data ),
                      HttpStatus.FORBIDDEN);
          }
        }
        return response;
    }

    private boolean isValidSequence(String sequence) {
        String regex = parameters.regex;
        return Pattern.matches(regex, sequence);
    }


    private void updateCountMutant(){
       Stats stats = statsRepository.getStats();
       stats.count_mutant_dna = stats.count_mutant_dna + 1;
       statsRepository.updateStats(stats);
    }

    private void updateCountHuman(){
        Stats stats = statsRepository.getStats();
        stats.count_human_dna = stats.count_human_dna + 1;
        statsRepository.updateStats(stats);
    }

}
