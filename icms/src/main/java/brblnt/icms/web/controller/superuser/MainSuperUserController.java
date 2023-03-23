package brblnt.icms.web.controller.superuser;

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
 * Controller for superuser request.
 * Use '/super-user' for main path.
 */
@Slf4j
@Controller
@RequestMapping(path = SUPER_USER_MAIN)
@RequiredArgsConstructor
public class MainSuperUserController {

  private final Utilities utilities;

  /**
   * Superusers home page. This page only available for admins and superusers.
   */
  @GetMapping(path = HOME_URL)
  @PreAuthorize(SUPER_USER_ROLE)
  public String getHome(Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(ROLE_FOR_CONTENT, SUPER_USER);
    model.addAttribute(MODEL_TOPBAR, actualUser);

    return INDEX;
  }

  /**
   * Superusers profile page.
   */
  @GetMapping(path = PROFILE_PAGE_URL)
  @PreAuthorize(SUPER_USER_ROLE)
  public String getProfile(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(ROLE_FOR_CONTENT, SUPER_USER);
    model.addAttribute(MODEL_CONTENT, PROFILE_PAGE);
    model.addAttribute(MODEL_TOPBAR, actualUser);

    return INDEX;
  }

}
