package brblnt.icms.service.service;

import brblnt.icms.data.properties.ConfigReader;
import brblnt.icms.web.model.modules.admin.AdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Admin Service.
 */
@Service
@RequiredArgsConstructor
public class AdminService {

  private final ConfigReader configReader;

  /**
   * Read db config data.
   */
  public String[] getDatabaseManage() {
    String[] array = {
            configReader.getPropertyFromConfigSystem("spring.datasource.url"),
            configReader.getPropertyFromConfigSystem("spring.datasource.username"),
            configReader.getPropertyFromConfigSystem("spring.datasource.password")};
    return array;
  }

  /**
   * Update db data in properties file.
   */
  public void saveData(AdminRequest adminRequest) {
    if (adminRequest.getUrl().equals("")) {
      return;
    }
    configReader.saveProperty(adminRequest.getUrl(), adminRequest.getUserName(), adminRequest.getPassword());
  }
}
