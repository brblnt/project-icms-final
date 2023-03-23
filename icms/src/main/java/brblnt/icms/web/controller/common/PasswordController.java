package brblnt.icms.web.controller.common;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.data.modules.users.model.ApplicationUserJPA;
import brblnt.icms.service.exceptions.UserNotFoundException;
import brblnt.icms.service.modules.users.repository.utility.ApplicationUsersRepositoryUtility;
import brblnt.icms.service.utilities.Utilities;
import brblnt.icms.web.model.modules.users.request.PasswordChangeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Password Controller.
 */
@Controller
@RequestMapping(path = PASSWORD_URL)
@RequiredArgsConstructor
public class PasswordController {

  private final PasswordEncoder passwordEncoder;
  private final ApplicationUsersRepositoryUtility applicationUsersRepositoryUtility;
  private final Utilities utilities;


  /**
   * Redirect user to password expired page.
   */
  @GetMapping(path = PASSWORD_EXPIRED)
  public String getExpiredPasswordChange(Authentication authentication) {
    if (utilities.getApplicationUser(authentication).getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_PAGE;
    }
    String homePath = utilities.getHomePathSuperUser(authentication);
    return  REDIRECT + "/" + homePath + HOME_URL;
  }

  /**
   * Update expired password.
   */
  @PostMapping(path = UPDATE_URL, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String getPasswordUpdate(
          Authentication authentication, final Model model, final PasswordChangeRequest passwordChangeRequest,
          final RedirectAttributes redirectAttributes) {

    ApplicationUserJPA actualUser = null;
    try {
      actualUser = applicationUsersRepositoryUtility.getUserByUsername(authentication.getName());
    } catch (UserNotFoundException e) {
      throw new RuntimeException(e);
    }


    if (passwordChangeRequest.getPassword().equals(passwordChangeRequest.getPasswordRe())) {
      actualUser.setPassword(passwordEncoder.encode(passwordChangeRequest.getPassword()));
      actualUser.setPasswordChanged(true);
      applicationUsersRepositoryUtility.updatePassword(actualUser, passwordEncoder.encode(passwordChangeRequest.getPassword()));
    } else {
      redirectAttributes.addFlashAttribute(SUCCESS, false);
      redirectAttributes.addFlashAttribute(MESSAGE_LINE_ONE, MESSAGE_PW_NOT_MATCH);
      return PASSWORD_EXPIRED_REDIRECT;
    }
    return PASSWORD_EXPIRED_REDIRECT;

  }
}
