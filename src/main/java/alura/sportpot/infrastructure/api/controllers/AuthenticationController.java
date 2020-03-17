package alura.sportpot.infrastructure.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alura.sportpot.infrastructure.api.forms.AuthenticationTokenResponse;
import alura.sportpot.infrastructure.api.forms.LoginUserForm;
import alura.sportpot.infrastructure.api.security.TokenService;

/**
 * AuthenticationController
 */
@RestController()
@RequestMapping("/login")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  private TokenService tokenService;

  @PostMapping("/user")
  public ResponseEntity<?> userLogin(@Valid @RequestBody LoginUserForm form) {
    UsernamePasswordAuthenticationToken token = form.buildAuthentication();

		try {
			Authentication authentication = authenticationManager.authenticate(token); 			
      String jwt = tokenService.generate(authentication);
			
			AuthenticationTokenResponse tokenResponse = new AuthenticationTokenResponse(jwt);
			return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
		
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário ou senha incorretos");
		}
    
  }
  
}