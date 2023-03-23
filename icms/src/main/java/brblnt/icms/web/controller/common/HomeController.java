package brblnt.icms.web.controller.common;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Home controller.
 * Redirect '/home', '/index' and '/' to main page.
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

  private final Utilities utilities;

  /**
   * Redirect home request to actual role home page.
   */
  @GetMapping(HOME_URL)
  public String getHome(final RedirectAttributes redirectAttributes, Authentication authentication, final Model model) {
    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    String homePath = utilities.getHomePath(authentication);
    redirectAttributes.addFlashAttribute(MODEL_TOPBAR, actualUser);
    return REDIRECT + "/" + homePath + HOME_URL;
  }

  /**
   * Redirect index to home.
   */
  @GetMapping(INDEX1)
  public String getIndex() {
    return REDIRECT + HOME_URL;
  }

  /**
   * Redirect index to home.
   */
  @GetMapping(INDEX2)
  public String getIndex2() {
    return REDIRECT + HOME_URL;
  }

}
