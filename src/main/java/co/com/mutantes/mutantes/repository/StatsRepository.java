package co.com.mutantes.mutantes.repository;

import co.com.mutantes.mutantes.model.Stats;

public interface StatsRepository {
     Stats getStats();
     void updateStats(Stats count);

}
