package alura.sportpot.infrastructure.api.forms;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import alura.sportpot.domain.entities.Championship;
import alura.sportpot.domain.entities.Team;

public class AddChampionshipForm {
  @NotBlank
  private String name;

  @FutureOrPresent
  private LocalDateTime startDate;

  @Min(1)
  private Integer numberOfTeams;

  private Collection<Long> teamIds;

  public String getName() {
    return name;
  }

  public Integer getNumberOfTeams() {
    return numberOfTeams;
  }

  public void setNumberOfTeams(Integer numberOfTeams) {
    this.numberOfTeams = numberOfTeams;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public Collection<Long> getTeamIds() {
    return teamIds;
  }

  public Championship build() {
    Set<Team> teams = this.teamIds == null ? 
      null :
      this.teamIds
        .stream()
        .map(teamId -> new Team(teamId))
        .collect(Collectors.toSet());

    return new Championship(this.name, this.startDate, this.numberOfTeams, teams);
  }
}
