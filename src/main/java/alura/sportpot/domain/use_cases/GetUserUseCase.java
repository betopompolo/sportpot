package alura.sportpot.domain.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.sportpot.domain.entities.User;
import alura.sportpot.domain.use_cases.exceptions.UserNotFoundException;
import alura.sportpot.infrastructure.database.UserRepository;

/**
 * GetUserUseCase
 */
@Service
public class GetUserUseCase {
  @Autowired
  private UserRepository userRepository;

  public User execute(String email) throws Exception {
    return userRepository.findFirstByEmail(email).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
  }
  
}