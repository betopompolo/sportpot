package alura.sportpot.domain.use_cases.exceptions;

/**
 * BetInviteAcceptedException
 */
public class BetInviteAcceptedException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = -1809912741173269506L;

  public BetInviteAcceptedException() {
    super();
  }

  public BetInviteAcceptedException(String message) {
    super(message);
  }
  
}