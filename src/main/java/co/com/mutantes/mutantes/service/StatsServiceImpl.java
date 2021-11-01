package co.com.mutantes.mutantes.service;

import co.com.mutantes.mutantes.model.Stats;
import co.com.mutantes.mutantes.repository.StatsRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public ResponseEntity<Object> getStats(){
        Stats stats =statsRepository.getStats();
        if(null != stats.count_human_dna && null != stats.count_mutant_dna && stats.count_human_dna > 0 ){
            stats.ratio = Double.valueOf (stats.count_mutant_dna/ stats.count_human_dna);
        }else {
            stats.ratio = 0D;
        }
        String message = new Gson().toJson(stats);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
