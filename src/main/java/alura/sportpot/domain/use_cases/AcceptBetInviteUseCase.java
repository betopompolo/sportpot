package alura.sportpot.domain.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import alura.sportpot.domain.entities.Bet;
import alura.sportpot.domain.entities.BetInvite;
import alura.sportpot.domain.entities.User;

/**
 * AcceptBetInviteUseCase
 */
@Service
public class AcceptBetInviteUseCase {

  @Autowired
  private GetBetInviteUseCase getBetInviteUseCase;

  @Autowired
  private GetUserUseCase userUseCase;

  @Transactional
  public void execute(Long inviteId) throws Exception {
    BetInvite invite = getBetInviteUseCase.execute(inviteId, true);
    User invitedUser = userUseCase.execute(invite.getEmailToInvite());
    Bet bet = invite.getBet();

    bet.addParticipant(invitedUser);



  }
  
}