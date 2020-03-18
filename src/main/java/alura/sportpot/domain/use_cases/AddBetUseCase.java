package alura.sportpot.domain.use_cases;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import alura.sportpot.domain.entities.Bet;
import alura.sportpot.domain.entities.BetInvite;
import alura.sportpot.infrastructure.database.BetRepository;

/**
 * AddBetUseCase
 */
@Service
public class AddBetUseCase {

  @Autowired
  private BetRepository betRepository;

  @Autowired
  private AddBetInviteUseCase addBetInviteUseCase;

  @Transactional
  public Bet execute(Bet bet) throws Exception {
    if (bet.getChampionship() == null) {
      throw new Exception("O campeonato não pode ser nulo");
    }

    if (bet.getCreatedBy() == null) {
      throw new Exception("O criador do bolão não foi encontrado");
    }

    Set<BetInvite> invites = bet.getInvites();

    if (invites == null || invites.isEmpty()) {
      throw new Exception("Nenhum convite encontrado no bolão");
    }

    Bet createdBet = betRepository.save(bet);

    Set<BetInvite> createdInvites = new HashSet<>();

    for (BetInvite betInvite : invites) {
      betInvite.setBet(createdBet);
      BetInvite createdInvite = addBetInviteUseCase.execute(betInvite);

      createdInvites.add(createdInvite);
    }

    createdBet.setInvites(createdInvites);

    return createdBet;
  }
  
}