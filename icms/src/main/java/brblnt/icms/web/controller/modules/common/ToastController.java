package brblnt.icms.web.controller.modules.common;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Toast controller.
 * '/toast'.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = TOAST_URL)
public class ToastController {

  private final Utilities utilities;

  /**
   * Get toast fragment.
   */
  @GetMapping(path = TOAST_PATH)
  public String getToast(
          Authentication authentication, final Model model,
          @PathVariable("type") String type,
          @PathVariable("title") String title,
          @PathVariable("small") String small,
          @PathVariable("text") String text,
          @PathVariable("color") String color) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    model.addAttribute(TOAST_TYPE, type);
    model.addAttribute(TOAST_COLOR, color);
    model.addAttribute(TOAST_HEADER, title);
    model.addAttribute(TOAST_MESSAGE, utilities.getMessage(text));
    model.addAttribute(TOAST_SMALL, utilities.getMessage(small));

    return TOAST_FRAGMENT;
  }

}
