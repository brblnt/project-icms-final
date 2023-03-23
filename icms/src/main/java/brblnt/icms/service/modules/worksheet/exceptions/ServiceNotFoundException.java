package brblnt.icms.service.modules.worksheet.exceptions;

/**
 * Not found exception.
 */
public class ServiceNotFoundException extends Exception {

  public ServiceNotFoundException() {
  }

  public ServiceNotFoundException(String message) {
    super(message);
  }
}
