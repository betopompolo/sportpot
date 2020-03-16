package alura.sportpot.infrastructure.api.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import alura.sportpot.domain.entities.User;


public class AddUserForm {
  @Email
  @NotEmpty
  private String login;

  @Size(min = 6)
  private String password;

  public User build() {
    return new User(login, password);
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
