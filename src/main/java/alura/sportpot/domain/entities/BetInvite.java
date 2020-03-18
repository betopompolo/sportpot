package alura.sportpot.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * BetInvite
 */
@Entity
public class BetInvite {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private LocalDateTime expirationDate;

  @ManyToOne
  @JsonBackReference
  private Bet bet;

  private String emailToInvite;

  private LocalDateTime acceptedAt;

  public BetInvite() {

  }
  
  public BetInvite(String emailToInvite) {
    this.emailToInvite = emailToInvite;
  }
  
  public LocalDateTime getAcceptedAt() {
    return acceptedAt;
  }

  public void setAcceptedAt(LocalDateTime acceptedAt) {
    this.acceptedAt = acceptedAt;
  }
  
  public Long getId() {
    return id;
  }

  public String getEmailToInvite() {
    return emailToInvite;
  }

  public void setEmailToInvite(String invitedEmail) {
    this.emailToInvite = invitedEmail;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDateTime expirationDate) {
    this.expirationDate = expirationDate;
  }

  public Bet getBet() {
    return bet;
  }
  
  public void setBet(Bet bet) {
    this.bet = bet;
  }
}