package alura.sportpot.domain.use_cases.exceptions;

/**
 * BetInviteExpiredException
 */
public class BetInviteExpiredException extends Exception {
  private static final long serialVersionUID = -6266449399126578947L;

  public BetInviteExpiredException() {
    super();
  }

  public BetInviteExpiredException(String message) {
    super(message);
  }
  
}