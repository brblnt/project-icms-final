package brblnt.icms.web.controller.admin;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.IcmsApplication;
import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.service.AdminService;
import brblnt.icms.service.utilities.Utilities;
import brblnt.icms.web.model.modules.admin.AdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Admin System Controller.
 * '/admin/system'.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = ADMIN_SYSTEM_DATABASE)
public class AdminSystemController {

  private final Utilities utilities;
  private final AdminService adminService;


  /**
   * Database management root page.
   */
  @GetMapping(path = ROOT_URL)
  @PreAuthorize(ADMIN_ROLE)
  public String database(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(DB_URL, adminService.getDatabaseManage()[0]);
    model.addAttribute(DB_USERNAME, adminService.getDatabaseManage()[1]);
    model.addAttribute(DB_PW, adminService.getDatabaseManage()[2]);


    model.addAttribute(ROLE_FOR_CONTENT, ADMIN);
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(MODEL_THEME, ADMIN_THEME);
    model.addAttribute(MODEL_CONTENT, DATABASE);
    return INDEX;
  }

  /**
   * Database config update.
   */
  @PostMapping(path = UPDATE_URL)
  @PreAuthorize(ADMIN_ROLE)
  public String databaseUpdate(
          Authentication authentication, final AdminRequest adminRequest) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    adminService.saveData(adminRequest);

    return REDIRECT_ADMIN_SYSTEM_DATABASE;
  }

  /**
   * Restart the application.
   */
  @GetMapping(ADMIN_SYSTEM_RESTART)
  @PreAuthorize(ADMIN_ROLE)
  public String restart() {
    IcmsApplication.restart();
    return LOGIN_REDIRECT;
  }

}
