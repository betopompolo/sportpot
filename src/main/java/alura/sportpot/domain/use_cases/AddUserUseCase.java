package alura.sportpot.domain.use_cases;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.sportpot.domain.entities.User;
import alura.sportpot.infrastructure.database.UserRepository;


@Service
public class AddUserUseCase {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private HashValueUseCase hashValueUseCase;

  @Transactional
  public User execute(User user) {
    String hashedPassword = hashValueUseCase.execute(user.getHashedPassword());
    user.setHashedPassword(hashedPassword);
    return this.userRepository.save(user);
  }
}