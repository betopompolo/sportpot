package alura.sportpot.domain.use_cases;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.sportpot.domain.entities.BetInvite;
import alura.sportpot.infrastructure.database.BetInviteRepository;


@Service
public class GetBetInviteUseCase {

  @Autowired
  private BetInviteRepository betInviteRepository;

  public BetInvite execute(Long inviteId) throws Exception {
    BetInvite betInvite = betInviteRepository
      .findById(inviteId)
      .orElseThrow(() -> new Exception("Convite não encontrado"));
    
    return betInvite;
  }

  public BetInvite execute(Long inviteId, boolean checkValidity) throws Exception {
    BetInvite betInvite = this.execute(inviteId);

    if (checkValidity) {
      checkInviteValidity(betInvite);
    }
    
    return betInvite;
  }
    
  private void checkInviteValidity(BetInvite betInvite) throws Exception {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime expirationDate = betInvite.getExpirationDate();

    if (now.isEqual(expirationDate) || now.isAfter(expirationDate)) {
      throw new Exception("Convite expirado");
    }
  }
  
}