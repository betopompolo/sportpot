package alura.sportpot.domain.use_cases.exceptions;

/**
 * UserNotFoundException
 */
public class UserNotFoundException extends Exception {
  private static final long serialVersionUID = 6038243739582755329L;

  public UserNotFoundException() {
    super();
  }

  public UserNotFoundException(String message) {
    super(message);
  }
}