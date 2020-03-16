package alura.sportpot.domain.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.sportpot.domain.entities.Team;
import alura.sportpot.infrastructure.database.TeamRepository;


@Service
public class AddTeamUseCase {

  @Autowired
  private TeamRepository teamRepository;

  public Team execute(Team team) {
    return teamRepository.save(team);
  }
}