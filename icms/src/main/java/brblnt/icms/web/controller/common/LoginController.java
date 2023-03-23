package brblnt.icms.web.controller.common;

import static brblnt.icms.service.utilities.Utilities.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Login controller return login pages.
 * '/login' path contains subpages like forgot password and failed auth. redirect.
 */
@Controller
@RequestMapping(path = LOGIN_URL)
public class LoginController {

  /**
   * Get login page.
   */
  @GetMapping(path = ROOT_URL)
  public String getLogin() {
    return LOGIN_PAGE;
  }

  /**
   * Get forgot password page.
   */
  @GetMapping(LOGIN_HELP)
  public String getForgotPassword() {
    return LOGIN_HELP_PAGE;
  }

  /**
   * Get login failed.
   */
  @GetMapping(path = LOGIN_FAILED)
  public String getLoginError() {
    return LOGIN_FAILED_PAGE;
  }

}
