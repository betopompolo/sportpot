package alura.sportpot.infrastructure.api.security;

import java.util.Collections;

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
    User user = getUserUseCase.execute(username);

    return org.springframework.security.core.userdetails.User
      .builder()
      .username(user.getEmail())
      .password(user.getHashedPassword())
      .authorities(Collections.emptyList())
      .build();
  }

  
}