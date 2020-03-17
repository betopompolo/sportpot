package alura.sportpot.infrastructure.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JwtAuthenticationFilter
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final String authHeader = "Authorization";
  private final TokenService tokenService;
  private final UserService userService;

  public JwtAuthenticationFilter(TokenService tokenService, UserService userService) {
    this.tokenService = tokenService;
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String jwt = request.getHeader(authHeader);
    boolean isJwtValid = tokenService.isValid(jwt);
    
    if (isJwtValid) {
      String userEmail = tokenService.getSubject(jwt);
      UserDetails userDetails = userService.loadUserByUsername(userEmail);
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        userDetails.getUsername(), 
        null, 
        userDetails.getAuthorities()
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  
}