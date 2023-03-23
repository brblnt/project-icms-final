package brblnt.icms.data.modules.common.populator;

import brblnt.icms.data.modules.common.model.CompanyJPA;
import brblnt.icms.data.modules.common.repository.CompanyRepository;
import brblnt.icms.data.populator.DatabasePopulator;
import brblnt.icms.data.properties.ConfigReader;
import brblnt.icms.service.modules.common.repository.utility.CompanyRepositoryUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Database Populate to populate database with sample data.
 */
@Component
@RequiredArgsConstructor
public class DatabasePopulatorSystem implements DatabasePopulator {

  private final ConfigReader configReader;
  private final CompanyRepository companyRepository;

  private final CompanyRepositoryUtility companyRepositoryUtility;

  @Override
  public void populateDatabase() {
    if (!configReader.getProp().isEmpty()) {
      if (!configReader.getPropertyFromConfig("system.boot.init.c").isEmpty()) {
        if (configReader.getPropertyFromConfig("system.boot.init.c").equals("true")) {
          if (companyRepositoryUtility.getAll().size() < 1) {
            CompanyJPA company = new CompanyJPA(
                    1L,
                    "setup company",
                    0,
                    0,
                    0,
                    0,
                    "XX XX/XXX-XXXX",
                    "XXXXXX@XXXXXX.XX",
                    "-",
                    "setup company"
            );
            companyRepository.save(company);
            configReader.saveProperty("system.boot.init.c", "false");
          }

        }
      }
    }
  }
}
