package alura.sportpot.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  private String email;

  @Size(min = 6)
  @JsonIgnore
  private String hashedPassword;
  
  @PastOrPresent
  private LocalDateTime createdAt = LocalDateTime.now();

  public User() {
    
  }
  
  public User(@NotEmpty @Email String email, @NotEmpty @Size(min = 6) String password) {
    this.email = email;
    this.hashedPassword = password;
  }

  public User(String email) {
    this.email = email;
  }

public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String login) {
    this.email = login;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public void setHashedPassword(String password) {
    this.hashedPassword = password;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}