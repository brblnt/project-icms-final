package brblnt.icms.data.populator;

import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

/**
 * DatabasePopulatorService.
 */
@Service
public class DatabasePopulatorService {

  private final List<DatabasePopulator> databasePopulators;

  public DatabasePopulatorService(final List<DatabasePopulator> databasePopulators) {
    this.databasePopulators = databasePopulators;
  }

  @PostConstruct
  public void populateDatabase() {
    databasePopulators.forEach(DatabasePopulator::populateDatabase);
  }

}
