package alura.sportpot.domain.use_cases;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.sportpot.domain.entities.BetInvite;
import alura.sportpot.infrastructure.database.BetInviteRepository;

@Service
public class AddBetInviteUseCase {

  @Autowired
  private BetInviteRepository betInviteRepository;

  private final Integer expirationInMillis = 86400000;
  
  
  public BetInvite execute(BetInvite betInvite) throws Exception {
    Date now = new Date();
    Date expirationDate = new Date(now.getTime() + expirationInMillis);
    LocalDateTime expirationDateTime = LocalDateTime.ofInstant(expirationDate.toInstant(), ZoneId.systemDefault());

    betInvite.setExpirationDate(expirationDateTime);
    return betInviteRepository.save(betInvite);
  }
  
}