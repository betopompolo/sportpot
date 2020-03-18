package alura.sportpot.infrastructure.api.forms;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import alura.sportpot.domain.entities.Bet;
import alura.sportpot.domain.entities.BetInvite;
import alura.sportpot.domain.entities.User;

public class AddBetForm {
  @NotNull
  private Long championshipId;

  @NotEmpty
  private Collection<@Email @NotNull String> emailsToInvite;

  public Long getChampionshipId() {
    return championshipId;
  }

  public Collection<String> getEmailsToInvite() {
    return emailsToInvite;
  }

  public Bet build(User loggedUser) {
    Set<BetInvite> invites = emailsToInvite.stream().map(email -> new BetInvite(email)).collect(Collectors.toSet());
    return new Bet(loggedUser, championshipId, invites);
  }
}
