package alura.sportpot.infrastructure.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alura.sportpot.domain.entities.User;
import alura.sportpot.domain.use_cases.AddUserUseCase;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private AddUserUseCase addUserUseCase;

  @PostMapping("/add")
  public User add(@Valid @RequestBody AddUserForm form) {
    return addUserUseCase.execute(form.build());
  }
}