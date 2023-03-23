package brblnt.icms.web.controller.admin;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller for admin request.
 * Use '/admin' for main path.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(ADMIN_MAIN)
public class MainAdminController {

  private final Utilities utilities;

  /**
   * Admins home contains main page with updates and information.
   * Only 'ROLE_ADMIN' can access this page.
   */
  @GetMapping(HOME_URL)
  @PreAuthorize(ADMIN_ROLE)
  public String getHome(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(ROLE_FOR_CONTENT, ADMIN);
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(MODEL_THEME, ADMIN_THEME);

    return INDEX;
  }

  /**
   * Profile page for admin users.
   * When admin user use SU or U pages they redirected their own page.
   * Only 'ROLE_ADMIN' can access this page.
   */
  @GetMapping(PROFILE_PAGE_URL)
  @PreAuthorize(ADMIN_ROLE)
  public String getProfile(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(ROLE_FOR_CONTENT, ADMIN);
    model.addAttribute(MODEL_CONTENT, PROFILE_PAGE);
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(MODEL_THEME, ADMIN_THEME);

    return INDEX;
  }

}
