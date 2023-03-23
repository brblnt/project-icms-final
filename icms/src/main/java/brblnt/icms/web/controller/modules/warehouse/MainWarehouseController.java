package brblnt.icms.web.controller.modules.warehouse;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main warehouse controller.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = WAREHOUSE_URL)
public class MainWarehouseController {

  private final Utilities utilities;

  /**
   * Get warehouse list.
   */
  @GetMapping(path = LIST_URL)
  public String getHome(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(ROLE_FOR_CONTENT, utilities.content(authentication));
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(MODEL_CONTENT, WAREHOUSE_MODULE);
    model.addAttribute(WAREHOUSE_CONTENT, LIST_CONTENT);

    return INDEX;
  }

}
