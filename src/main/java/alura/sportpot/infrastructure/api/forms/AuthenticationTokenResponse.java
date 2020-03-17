package alura.sportpot.infrastructure.api.forms;

/**
 * JwtTokenResponse
 */
public class AuthenticationTokenResponse {

  private String token;

  public AuthenticationTokenResponse(String token) {
    this.token = token;
  }

  /**
   * @return the token
   */
  public String getToken() {
    return token;
  }
}