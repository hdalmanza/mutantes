package co.com.mutantes.mutantes.repository;

import co.com.mutantes.mutantes.model.Stats;
import org.springframework.stereotype.Repository;

@Repository
public class StatsRepositoryImpl implements StatsRepository{

    @Override
    public Stats getStats(){
        Stats stats = new Stats();
        stats.count_human_dna = 40;
        stats.count_mutant_dna = 50;
        return  stats;
    }

    @Override
    public void updateStats(Stats count) {

    }


}
