package alura.sportpot.infrastructure.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import alura.sportpot.domain.entities.User;
import alura.sportpot.domain.use_cases.GetUserUseCase;

/**
 * UserDetailsService
 */
@Service
public class UserService implements UserDetailsService {

  @Autowired
  private GetUserUseCase getUserUseCase;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      User user = getUserUseCase.execute(username);
      return new LoggedUser(user);
    } catch (Exception e) {
      throw new UsernameNotFoundException(e.getMessage());
    }
  }

  
}