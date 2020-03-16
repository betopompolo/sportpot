package alura.sportpot.domain.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.sportpot.domain.entities.Championship;
import alura.sportpot.infrastructure.database.ChampionshipRepository;

/**
 * AddChampionshipUseCase
 */
@Service
public class AddChampionshipUseCase {
  @Autowired
  private ChampionshipRepository championshipRepository;

  public Championship execute(Championship championship) {
    return championshipRepository.save(championship);
  }
  
}