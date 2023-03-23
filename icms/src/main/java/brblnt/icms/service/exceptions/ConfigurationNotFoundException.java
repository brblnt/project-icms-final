package brblnt.icms.service.exceptions;

/**
 * Configuration not found  exception.
 */
public class ConfigurationNotFoundException extends Exception {

  public ConfigurationNotFoundException() {
  }

  public ConfigurationNotFoundException(String message) {
    super(message);
  }
}
