package alura.sportpot.infrastructure.database;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import alura.sportpot.domain.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
  
  @Query(value = "select * from User where email=:email limit 1", nativeQuery = true)
  public User findByEmail(@Param("email") String email);

}