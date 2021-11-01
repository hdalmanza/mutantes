package co.com.mutantes.mutantes.repository;

import co.com.mutantes.mutantes.model.Stats;
import org.bson.conversions.Bson;

public interface StatsRepository {
     Stats getStats();
     void updateStats(Bson updateValue);

}
