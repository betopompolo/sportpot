package alura.sportpot.infrastructure.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import alura.sportpot.domain.entities.Championship;
import alura.sportpot.domain.use_cases.AddChampionshipUseCase;
import alura.sportpot.infrastructure.api.forms.AddChampionshipForm;

/**
 * ChampionshipController
 */
@RestController("/championship")
public class ChampionshipController {
  @Autowired
  private AddChampionshipUseCase addChampionshipUseCase;


  @PostMapping("/add")
  Championship add(@Valid @RequestBody AddChampionshipForm form) {
    return addChampionshipUseCase.execute(form.build());
  }
  
}