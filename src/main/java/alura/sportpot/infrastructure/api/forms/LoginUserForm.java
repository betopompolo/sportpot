package alura.sportpot.infrastructure.api.forms;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginUserForm {
  @NotBlank
  public String login;

  @NotBlank
  public String password;

  public UsernamePasswordAuthenticationToken buildAuthentication() {
    return new UsernamePasswordAuthenticationToken(login, password);
  }
}
