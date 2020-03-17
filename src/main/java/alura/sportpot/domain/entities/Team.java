package alura.sportpot.domain.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  @Column(unique = true, nullable = false)
  private String name;

  @Past
  @NotNull
  private LocalDateTime foundationDate;

  @ManyToMany(mappedBy = "teams")
  private Set<Championship> championships;


  public Team(Long id) {
    this.id = id;
  }

  public Team(@NotEmpty String name, @NotEmpty @Past LocalDateTime foundationDate) {
    this.name = name;
    this.foundationDate = foundationDate;
  }

  public Set<Championship> getChampionships() {
    return championships;
  }

  public void setChampionships(Set<Championship> championships) {
    this.championships = championships;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
