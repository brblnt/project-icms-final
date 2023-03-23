package brblnt.icms.service.modules.worksheet.exceptions;

/**
 * Not found exception.
 */
public class CustomerNotFoundException extends Exception {

  public CustomerNotFoundException() {
  }

  public CustomerNotFoundException(String message) {
    super(message);
  }
}
