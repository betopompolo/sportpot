package alura.sportpot.infrastructure.database;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import alura.sportpot.domain.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
  public Optional<User> findFirstByEmail(String email);

}