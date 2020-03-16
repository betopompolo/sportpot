package alura.sportpot.domain.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.sportpot.domain.entities.User;
import alura.sportpot.infrastructure.database.UserRepository;

/**
 * GetUserUseCase
 */
@Service
public class GetUserUseCase {
  @Autowired
  private UserRepository userRepository;

  public User execute(String email) {
    return userRepository.findByEmail(email);
  }
  
}