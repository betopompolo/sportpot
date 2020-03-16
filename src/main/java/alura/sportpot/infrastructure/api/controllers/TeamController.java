package alura.sportpot.infrastructure.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import alura.sportpot.domain.entities.Team;
import alura.sportpot.domain.use_cases.AddTeamUseCase;
import alura.sportpot.infrastructure.api.forms.AddTeamForm;

@RestController("/team")
public class TeamController {
  @Autowired
  private AddTeamUseCase addTeamUseCase;

  @PostMapping
  public Team add(@Valid @RequestBody AddTeamForm form) {
    return this.addTeamUseCase.execute(form.build());
  }
  
}