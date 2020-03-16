package alura.sportpot.infrastructure.api.forms;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import alura.sportpot.domain.entities.Team;

public class AddTeamForm {

  @NotBlank
  private String name;

  @Past
  @NotNull
  private LocalDateTime foundationDate;

  public LocalDateTime getFoundationDate() {
    return foundationDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setFoundationDate(LocalDateTime foundationDate) {
    this.foundationDate = foundationDate;
  }

  public Team build() {
    return new Team(name, foundationDate);
  }

}
