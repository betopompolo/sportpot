package alura.sportpot.domain.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;

@Entity
public class Championship {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @FutureOrPresent
  @Column(nullable = false)
  private LocalDateTime startDate = LocalDateTime.now();

  @Column(nullable = false)
  private Integer numberOfTeams;

  @ManyToMany
  @JoinTable(
    name = "championship_team",
    joinColumns = @JoinColumn(name = "championship_id"),
    inverseJoinColumns = @JoinColumn(name = "team_id")
  )
  private Set<Team> teams;

  public Championship() {

  }

  public Championship(Long id) {
    this.id = id;
  }

  public Championship(@NotBlank String name, @FutureOrPresent LocalDateTime startDate, Integer numberOfTeams) {
    this.name = name;
    this.startDate = startDate;
    this.numberOfTeams = numberOfTeams;
  }

  public Championship(@NotBlank String name, @FutureOrPresent LocalDateTime startDate, Integer numberOfTeams, Set<Team> teams) {
    this.name = name;
    this.startDate = startDate;
    this.numberOfTeams = numberOfTeams;
    this.teams = teams;
  }

  public Set<Team> getTeams() {
    return teams;
  }

  public void setTeams(Set<Team> teams) {
    this.teams = teams;
  }

  public String getName() {
    return name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
}
