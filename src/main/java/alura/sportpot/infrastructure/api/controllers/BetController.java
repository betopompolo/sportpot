package alura.sportpot.infrastructure.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alura.sportpot.domain.entities.Bet;
import alura.sportpot.domain.use_cases.AddBetUseCase;
import alura.sportpot.infrastructure.api.forms.AddBetForm;
import alura.sportpot.infrastructure.api.security.LoggedUser;

/**
 * BetController
 */
@RestController
@RequestMapping("/bet")
public class BetController {

  @Autowired
  private AddBetUseCase addBetUseCase;

  @PostMapping("/add")
  public Bet add(@Valid @RequestBody AddBetForm form, Authentication authentication) throws Exception {
    LoggedUser loggedUser = (LoggedUser) authentication.getPrincipal();
    return addBetUseCase.execute(form.build(loggedUser.getUser()));
  }
}