package alura.sportpot.domain.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Bet
 */
@Entity
public class Bet {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(optional = false)
  private User createdBy;

  private LocalDateTime createdAt = LocalDateTime.now();

  @ManyToOne
  private Championship championship;

  @OneToMany(mappedBy = "bet")
  @JsonManagedReference
  private Set<BetInvite> invites;

  @ManyToMany
  @JoinTable(
    name = "bet_user",
    joinColumns = @JoinColumn(name = "bet_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private Set<User> participants;

  public Bet() {

  }

  public Bet(User createdBy, Long championshipId, Set<BetInvite> invites) {
    this.createdBy = createdBy;
    this.championship = new Championship(championshipId);
    this.invites = invites;
  }

  public Long getId() {
    return id;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public Championship getChampionship() {
    return championship;
  }

  public Set<BetInvite> getInvites() {
    return invites;
  }

  public void setInvites(Set<BetInvite> invites) {
    this.invites = invites;
  }

  public Set<User> getParticipants() {
    return participants;
  }

  public void addParticipant(User participant) {
    this.participants.add(participant);
  }
}