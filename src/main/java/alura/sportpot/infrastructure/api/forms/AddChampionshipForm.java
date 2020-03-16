package alura.sportpot.infrastructure.api.forms;

import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import alura.sportpot.domain.entities.Championship;

public class AddChampionshipForm {
  @NotBlank
  private String name;

  @FutureOrPresent
  private LocalDateTime startDate;

  @Min(1)
  private Integer numberOfTeams;

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

  public Championship build() {
    return new Championship(this.name, this.startDate, this.numberOfTeams);
  }
}
