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

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  private String login;

  @Size(min = 6)
  private String hashedPassword;
  
  @PastOrPresent
  private LocalDateTime createdAt = LocalDateTime.now();    
  
  public User(@NotEmpty @Email String login, @NotEmpty @Size(min = 6) String password) {
    this.login = login;
    this.hashedPassword = password;
  }

  public Long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public void setHashedPassword(String password) {
    this.hashedPassword = password;
  }
}