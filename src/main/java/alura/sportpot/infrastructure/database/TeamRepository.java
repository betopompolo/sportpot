package alura.sportpot.infrastructure.database;

import org.springframework.data.repository.CrudRepository;

import alura.sportpot.domain.entities.Team;

public interface TeamRepository  extends CrudRepository<Team, Long>{

  
}