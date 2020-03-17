package alura.sportpot.infrastructure.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import alura.sportpot.domain.entities.User;
import alura.sportpot.domain.use_cases.LoginUserUseCase;

/**
 * HashedAuthenticationManager
 */
@Service
public class HashedAuthenticationManager implements AuthenticationManager {

  @Autowired
  private LoginUserUseCase loginUseCase;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String login = authentication.getPrincipal().toString();
    String password = authentication.getCredentials().toString();
    
    try {
      User user = loginUseCase.execute(new User(login, password));      
      
      return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getHashedPassword());
    } catch (Exception e) {
      throw new BadCredentialsException("Usuário ou senha incorretos");
    }
  }

  
}