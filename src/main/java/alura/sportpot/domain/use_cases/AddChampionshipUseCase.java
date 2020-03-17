package alura.sportpot.domain.use_cases;

import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.sportpot.domain.entities.Championship;
import alura.sportpot.domain.entities.Team;
import alura.sportpot.infrastructure.database.ChampionshipRepository;

/**
 * AddChampionshipUseCase
 */
@Service
public class AddChampionshipUseCase {
  @Autowired
  private ChampionshipRepository championshipRepository;

  public Championship execute(Championship championship) throws Exception {
    validateTeams(championship);
    
    Championship createdChampionship = championshipRepository.save(championship);

    Hibernate.initialize(createdChampionship.getTeams());

    return createdChampionship;
  }

  private void validateTeams(Championship championship) throws Exception {
    Set<Team> teams = championship.getTeams();
    Integer numberOfTeams = championship.getNumberOfTeams();
    if (teams == null) {
      return;
    }

    if (teams.size() != numberOfTeams) {
      throw new Exception("O número de times do campeonato é diferente do número de times informados no cadastro");
    }
  }
  
}