package alura.sportpot.infrastructure.database;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import alura.sportpot.domain.entities.BetInvite;


public interface BetInviteRepository extends CrudRepository<BetInvite, Long> {
  Optional<BetInvite> findById(Long id);
}