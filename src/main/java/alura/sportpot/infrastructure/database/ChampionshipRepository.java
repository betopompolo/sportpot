package alura.sportpot.infrastructure.database;

import org.springframework.data.repository.CrudRepository;

import alura.sportpot.domain.entities.Championship;



/**
 * ChampionshipRepository
 */
public interface ChampionshipRepository extends CrudRepository<Championship, Long>{

  
}