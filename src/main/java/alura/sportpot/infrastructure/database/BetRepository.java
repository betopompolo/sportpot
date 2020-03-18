package alura.sportpot.infrastructure.database;

import org.springframework.data.repository.CrudRepository;

import alura.sportpot.domain.entities.Bet;


public interface BetRepository extends CrudRepository<Bet, Long>{
  
}