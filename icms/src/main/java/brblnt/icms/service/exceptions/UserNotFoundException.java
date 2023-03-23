package brblnt.icms.service.exceptions;

/**
 * User not found by username exception.
 * When database not contains the expected username throw this exception.
 */
public class UserNotFoundException extends Exception {

  public UserNotFoundException() {
  }

  public UserNotFoundException(String message) {
    super(message);
  }
}
