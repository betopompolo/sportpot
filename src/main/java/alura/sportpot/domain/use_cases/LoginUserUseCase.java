package alura.sportpot.domain.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.sportpot.domain.entities.User;
import alura.sportpot.infrastructure.database.UserRepository;


/**
 * LoginUserUseCase
 */
@Service
public class LoginUserUseCase {
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private HashValueUseCase hashValueUseCase;
  

  public User execute(User inputUser) throws Exception {
    if (inputUser == null) {
      throw new Exception();
    }

    String inputHashedPassword = hashValueUseCase.execute(inputUser.getHashedPassword());
    User user = userRepository.findByEmail(inputUser.getEmail());
    Boolean isAuthenticated = user.getHashedPassword().equals(inputHashedPassword);

    return isAuthenticated ? user : null;
  }
}
