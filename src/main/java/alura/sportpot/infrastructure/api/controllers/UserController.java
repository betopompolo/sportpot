package alura.sportpot.infrastructure.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import alura.sportpot.domain.entities.User;
import alura.sportpot.domain.use_cases.AddUserUseCase;
import alura.sportpot.domain.use_cases.LoginUserUseCase;
import alura.sportpot.infrastructure.api.forms.AddUserForm;
import alura.sportpot.infrastructure.api.forms.LoginUserForm;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private AddUserUseCase addUserUseCase;

  @Autowired
  private LoginUserUseCase loginUserUseCase;

  @PostMapping("/login")
  public void login(@Valid @RequestBody LoginUserForm form) {
    Boolean isLoginSuccess = loginUserUseCase.execute(form.build());
    if (!isLoginSuccess) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário ou senha incorretos");
    }
  }

  @PostMapping("/add")
  public User add(@Valid @RequestBody AddUserForm form) {
    return addUserUseCase.execute(form.build());
  }
}