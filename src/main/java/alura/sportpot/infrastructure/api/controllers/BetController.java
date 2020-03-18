package alura.sportpot.infrastructure.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alura.sportpot.domain.entities.Bet;
import alura.sportpot.domain.entities.User;
import alura.sportpot.domain.use_cases.AcceptBetInviteUseCase;
import alura.sportpot.domain.use_cases.AddBetUseCase;
import alura.sportpot.domain.use_cases.SendBetInviteUseCase;
import alura.sportpot.domain.use_cases.exceptions.BetInviteExpiredException;
import alura.sportpot.domain.use_cases.exceptions.UserNotFoundException;
import alura.sportpot.infrastructure.api.forms.AddBetForm;
import alura.sportpot.infrastructure.api.security.LoggedUser;
import springfox.documentation.annotations.ApiIgnore;

/**
 * BetController
 */
@RestController
@RequestMapping("/bet")
public class BetController {

  @Autowired
  private AddBetUseCase addBetUseCase;

  @Autowired
  private SendBetInviteUseCase sendBetInviteUseCase;

  @Autowired
  private AcceptBetInviteUseCase acceptBetInviteUseCase;

  @PostMapping("/add")
  public Bet add(@Valid @RequestBody AddBetForm form, @ApiIgnore Authentication authentication) throws Exception {
    LoggedUser loggedUser = (LoggedUser) authentication.getPrincipal();
    User user = loggedUser.getUser();
    
    Bet createdBet = addBetUseCase.execute(form.build(user));
    sendBetInviteUseCase.execute(createdBet.getInvites());

    return createdBet;
  }

  @GetMapping("/accept-invite")
  public ResponseEntity<String> acceptInvite(@RequestParam(required = true) Long inviteId) {
    try {
      acceptBetInviteUseCase.execute(inviteId);
      return ResponseEntity.ok("Invite accepted! :D");
    } catch (Exception e) {
      if (e instanceof UserNotFoundException) {
        return ResponseEntity.ok("Go to register page");
      }

      if (e instanceof BetInviteExpiredException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível aceitar o convite");
    }
  }
}