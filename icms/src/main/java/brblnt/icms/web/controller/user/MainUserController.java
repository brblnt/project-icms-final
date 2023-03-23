package brblnt.icms.web.controller.user;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for user request.
 * Use '/user' for main path.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = USER_MAIN)
public class MainUserController {


  private final Utilities utilities;

  /**
   * Simple users home. You can be able to access from admin and simple user role.
   * Superusers can't access this page because they also have this functions on their own page.
   */
  @GetMapping(path = HOME_URL)
  @PreAuthorize(SIMPLE_USER_ROLE)
  public String getHome(Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(ROLE_FOR_CONTENT, SIMPLE_USER);
    model.addAttribute(MODEL_TOPBAR, actualUser);
    return INDEX;
  }

  /**
   * Get Simple User profile page.
   */
  @GetMapping(path = PROFILE_PAGE_URL)
  public String getProfile(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(ROLE_FOR_CONTENT, SIMPLE_USER);
    model.addAttribute(MODEL_CONTENT, PROFILE_PAGE);
    model.addAttribute(MODEL_TOPBAR, actualUser);

    return INDEX;
  }


}
