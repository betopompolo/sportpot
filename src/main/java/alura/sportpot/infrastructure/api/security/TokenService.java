package alura.sportpot.infrastructure.api.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * TokenService
 */
@Service
public class TokenService {

  @Value("${alura.sportpot.jwt.secret}")
  private String jwtSecret;

  @Value("${alura.sportpot.jwt.expiration-milli-sec}")
  private long expirationInMilliSec;

  private final String issuer = "Sportpot";


  public String generate(Authentication auth) {
    String userEmail = (String) auth.getPrincipal();

    final Date now = new Date();
		final Date expiration = new Date(now.getTime() + expirationInMilliSec);
    
    return Jwts.builder()
			.setIssuer(issuer)
			.setSubject(userEmail)
			.setIssuedAt(now)
			.setExpiration(expiration)
			.signWith(SignatureAlgorithm.HS256, this.jwtSecret)
			.compact();
  }

  public Boolean isValid(String jwt) {
		try {
			Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(jwt);		
			return true;	
		} catch (JwtException | IllegalArgumentException e) {			
			return false;
		}
  }

  public String getSubject(String jwt) {
    Claims claims = Jwts
      .parser()
      .setSigningKey(this.jwtSecret)
      .parseClaimsJws(jwt).getBody();
		
		return claims.getSubject();
  }

  
}