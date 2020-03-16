package alura.sportpot.infrastructure.api.forms;

import javax.validation.constraints.NotBlank;

import alura.sportpot.domain.entities.User;

public class LoginUserForm {
  @NotBlank
  public String login;

  @NotBlank
  public String password;

  public User build() {
    return new User(login, password);
  }
}
