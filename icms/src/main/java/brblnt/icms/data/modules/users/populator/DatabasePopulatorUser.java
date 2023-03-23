package brblnt.icms.data.modules.users.populator;

import brblnt.icms.config.security.PasswordGenerator;
import brblnt.icms.data.modules.users.ApplicationUserRepository;
import brblnt.icms.data.modules.users.model.ApplicationUserJPA;
import brblnt.icms.data.populator.DatabasePopulator;
import brblnt.icms.data.properties.ConfigReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * DatabasePopulator for users create config-admin user.
 */
@Component
@RequiredArgsConstructor
public class DatabasePopulatorUser implements DatabasePopulator {

  private final ConfigReader configReader;
  private final PasswordGenerator passwordGenerator;
  private final ApplicationUserRepository applicationUserRepository;

  @Override
  public void populateDatabase() {
    if (!configReader.getProp().isEmpty()) {
      if (!configReader.getPropertyFromConfig("system.boot.init.a").isEmpty()) {
        if (configReader.getPropertyFromConfig("system.boot.init.a").equals("true")) {
          ApplicationUserJPA admin = new ApplicationUserJPA(
                  1L,
                  "CONFIG",
                  "ADMIN",
                  "conf-admin",
                  "admin@admin.admin",
                  passwordGenerator.encode("password"),
                  "ADMIN",
                  true,
                  "config-admin",
                  true,
                  true,
                  true,
                  true);
          applicationUserRepository.save(admin);
        }
        configReader.saveProperty("system.boot.init.a", "false");
      }
    }
  }

}
