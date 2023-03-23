package brblnt.icms.data.properties;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import brblnt.icms.service.exceptions.ConfigurationNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Configuration Reader. Read keys from a properties file.
 */
@Service
@Slf4j
public class ConfigReader {
  private final Properties PROPERTIES = new Properties();
  private final Properties SYSTEM = new Properties();
  private final String NAME_PROPERTIES_FILE = "config.properties";
  private final String NAME_SYSTEM_PROPERTIES_FILE = "application.properties";

  @Autowired
  private void readProperties() throws ConfigurationNotFoundException {
    try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(NAME_PROPERTIES_FILE)) {
      PROPERTIES.load(input);
    } catch (Exception ex) {
      //throw new ConfigurationNotFoundException("Cannot load configuration file! File not found!");
    }
    try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(NAME_SYSTEM_PROPERTIES_FILE)) {
      SYSTEM.load(input);
    } catch (Exception ex) {
      //throw new ConfigurationNotFoundException("Cannot load configuration file! File not found!");
    }
  }

  private void saveProperties() {
    try {
      PROPERTIES.store(new FileOutputStream("src/main/resources/config.properties"), null);
      SYSTEM.store(new FileOutputStream("src/main/resources/application.properties"), null);
    } catch (IOException ex) {
      System.out.println(ex);
    }

  }

  /**
   * Get property file.
   *
   * @return Properties object.
   */
  public Properties getProp() {
    return PROPERTIES;
  }

  /**
   * Get properties by key.
   *
   * @param key property key.
   * @return values of the property.
   */
  public String getPropertyFromConfig(String key) {
    return PROPERTIES.getProperty(key);
  }

  public String getPropertyFromConfigSystem(String key) {
    return SYSTEM.getProperty(key);
  }

  /**
   * Update key in the file and save it.
   *
   * @param key key.
   * @param value new value.
   */
  public void saveProperty(String key, String value) {
    PROPERTIES.setProperty(key, value);
    saveProperties();
  }

  /**
   * Update database properties.
   */
  public void saveProperty(String url, String user, String password) {
    SYSTEM.setProperty("spring.datasource.url", url);
    SYSTEM.setProperty("spring.datasource.username", user);
    SYSTEM.setProperty("spring.datasource.password", password);
    saveProperties();
  }

}
