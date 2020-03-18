package alura.sportpot.infrastructure.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import alura.sportpot.domain.entities.User;
import alura.sportpot.domain.use_cases.AddUserUseCase;
import alura.sportpot.infrastructure.api.forms.AddUserForm;

@Controller
@RequestMapping("/user")
public class UserController {
  @Autowired
  private AddUserUseCase addUserUseCase;

  @PostMapping("/add")
  public User add(@Valid @RequestBody AddUserForm form) {
    return addUserUseCase.execute(form.build());
  }

  @GetMapping("/add")
  public String showAddUserForm(@RequestParam Long inviteId, Model model) {
    model.addAttribute("inviteId", inviteId);
    model.addAttribute("user", new AddUserForm());
    return "add-user";
  }

  @PostMapping("/add/submit")
  public RedirectView addUserFormSubmit(@Valid @ModelAttribute("user") AddUserForm form, @RequestParam Long inviteId, BindingResult result) throws Exception {
    if (result.hasErrors()) {
      return new RedirectView("/user/add?inviteId="+inviteId, true);
    }
    addUserUseCase.execute(form.build());
    return new RedirectView("/bet/accept-invite?inviteId="+inviteId, true);
  }
}