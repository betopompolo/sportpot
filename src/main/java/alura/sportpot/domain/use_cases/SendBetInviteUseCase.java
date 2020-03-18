package alura.sportpot.domain.use_cases;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.sportpot.domain.entities.BetInvite;
import alura.sportpot.infrastructure.email.EmailService;

/**
 * SendBetInviteUseCase
 */
@Service
public class SendBetInviteUseCase {

  @Autowired
  private EmailService emailService;

  public void execute(BetInvite betInvite) throws Exception {
    try {
      emailService.sendMessage(
        betInvite.getEmailToInvite(), 
        "Você foi convidado para um bolão!", 
        "Entre no link para aceitar o convite"
      );
    } catch (Exception e) {
      throw new Exception("Não foi possível enviar os convites do bolão");
    }
  }

  public void execute(Collection<BetInvite> invites) throws Exception {
    for (BetInvite betInvite : invites) {
      this.execute(betInvite);
    }
  }
}