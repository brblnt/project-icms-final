package brblnt.icms.web.controller.superuser;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.service.modules.common.CommonCompanyService;
import brblnt.icms.service.utilities.Utilities;
import brblnt.icms.web.model.modules.common.request.CompanyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Super-user company controller.
 * '/super-user/settings'.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = SUPER_USER_BASICS)
public class CompanyController {

  private final Utilities utilities;
  private final CommonCompanyService commonCompanyService;

  /**
   * Get company setup page.
   */
  @GetMapping(path = ROOT_URL)
  @PreAuthorize(SUPER_USER_ROLE)
  public String getBasics(Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(ROLE_FOR_CONTENT, SUPER_USER);
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(SUPER_USER_CONTENT, BASIC_INFORMATION_SETTINGS);
    model.addAttribute(SUPER_USER_COMPANY, commonCompanyService.getById(0L));

    return INDEX;
  }

  /**
   * Update company data.
   */
  @PostMapping(path = UPDATE_URL, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @PreAuthorize(SUPER_USER_ROLE)
  public String postBasics(Authentication authentication, final CompanyRequest companyRequest,
                           final RedirectAttributes redirectAttributes) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    boolean success = commonCompanyService.save(companyRequest, null);

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_SAVE_COMPANY);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_SAVE_COMPANY);
    }

    return REDIRECT_SUPER_USER_BASICS;
  }

}
