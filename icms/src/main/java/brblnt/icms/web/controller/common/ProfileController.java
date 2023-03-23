package brblnt.icms.web.controller.common;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.service.ProfileUpdater;
import brblnt.icms.service.utilities.Utilities;
import brblnt.icms.web.model.modules.users.request.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Profile controller.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = PROFILE_URL)
public class ProfileController {


  private final Utilities utilities;
  private final ProfileUpdater profileUpdater;


  /**
   * Redirect users to own profile page.
   */
  @GetMapping(path = PAGE_URL)
  public String getHome(final RedirectAttributes redirectAttributes, Authentication authentication) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    String homePath = utilities.getHomePathSuperUser(authentication);
    redirectAttributes.addFlashAttribute(MODEL_TOPBAR, actualUser);

    return REDIRECT + "/" + homePath + "/" + PROFILE_PAGE;
  }

  /**
   * Update profile.
   */
  @PostMapping(path = UPDATE_URL, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String postProfileUpdate(
          Authentication authentication, final ProfileUpdateRequest profileUpdateRequest,
          final RedirectAttributes redirectAttributes) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    String homePath = utilities.getHomePathSuperUser(authentication);

    boolean success = profileUpdater.saveUserWithNewData(actualUser, profileUpdateRequest);


    if (success) {
      redirectAttributes.addFlashAttribute(SUCCESS, true);
      redirectAttributes.addFlashAttribute(MESSAGE_LINE_ONE, MESSAGE_SUCCESS);
    } else {
      redirectAttributes.addFlashAttribute(SUCCESS, false);
      redirectAttributes.addFlashAttribute(MESSAGE_LINE_ONE, MESSAGE_UNSUCCESS);
      redirectAttributes.addFlashAttribute(MESSAGE_LINE_TWO, MESSAGE_PW_NOT_MATCH);
    }

    return REDIRECT + "/" + homePath + "/" + PROFILE_PAGE;
  }


}
