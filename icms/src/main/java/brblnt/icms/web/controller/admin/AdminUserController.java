package brblnt.icms.web.controller.admin;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.service.AdminUserService;
import brblnt.icms.service.utilities.Utilities;
import brblnt.icms.web.model.modules.users.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Admins User Controller.
 * '/admin/user'.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = ADMIN_USER)
public class AdminUserController {
  private final AdminUserService adminUserService;
  private final Utilities utilities;


  /**
   * Admin user list page.
   */
  @GetMapping(path = LIST_URL)
  @PreAuthorize(ADMIN_ROLE)
  public String getUserList(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(ROLE_FOR_CONTENT, ADMIN);
    model.addAttribute(MODEL_THEME, ADMIN_THEME);
    model.addAttribute(MODEL_CONTENT, USER_MANAGE);
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(ALL_USER, adminUserService.getAll());

    return INDEX;
  }

  /**
   * Admin user create page.
   */
  @GetMapping(path = CREATE_URL)
  @PreAuthorize(ADMIN_ROLE)
  public String getUserCreate(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(ROLE_FOR_CONTENT, ADMIN);
    model.addAttribute(MODEL_THEME, ADMIN_THEME);
    model.addAttribute(MODEL_CONTENT, USER_CREATE);
    model.addAttribute(MODEL_TOPBAR, actualUser);

    return INDEX;
  }

  /**
   * Admin user create with post method.
   */
  @PostMapping(path = CREATE_URL, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @PreAuthorize(ADMIN_ROLE)
  public String getUserCreate(
          Authentication authentication, final UserRequest userRequest,
          final RedirectAttributes redirectAttributes) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    if (adminUserService.exist(userRequest.getUserName())) {
      redirectAttributes.addFlashAttribute(SUCCESS, false);
      redirectAttributes.addFlashAttribute(MESSAGE_LINE_ONE, MESSAGE_USERNAME);
      return REDIRECT_ADMIN_USER_CREATE;
    }

    CompleteUser user = adminUserService.create(userRequest);
    String[] array = user.getUserName().split(";");

    if (!adminUserService.isNameOrEmailEmpty(userRequest)) {
      redirectAttributes.addFlashAttribute(SUCCESS, true);
      redirectAttributes.addFlashAttribute(MESSAGE_LINE_ONE, MESSAGE_SUCCESS);
      redirectAttributes.addFlashAttribute(MESSAGE_LINE_TWO, "felhasználónév: " + array[0] + " jelszó: " + array[1] + "");
    } else {
      redirectAttributes.addFlashAttribute(SUCCESS, false);
      redirectAttributes.addFlashAttribute(MESSAGE_LINE_ONE, MISSING_INFORMATION);
    }
    return REDIRECT_ADMIN_USER_CREATE;
  }

  /**
   * Admin user edit page.
   */
  @GetMapping(path = EDIT_URL)
  @PreAuthorize(ADMIN_ROLE)
  public String getUserEdit(
          Authentication authentication, final Model model, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    CompleteUser user = null;

    if (adminUserService.exist(id)) {
      user = adminUserService.getById(id);
    } else {
      String messageFail = "Ilyen ID = " + id + " -val nem található felhasználó!";
      redirectAttributes.addFlashAttribute(SUCCESS, false);
      redirectAttributes.addFlashAttribute(MESSAGE_LINE_ONE, messageFail);
      return REDIRECT_ADMIN_USER_LIST;
    }


    model.addAttribute(ROLE_FOR_CONTENT, ADMIN);
    model.addAttribute(MODEL_THEME, ADMIN_THEME);
    model.addAttribute(MODEL_CONTENT, USER_EDIT);
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(USER_EDIT_MODEL, user);

    return INDEX;
  }


  /**
   * Admin user update with post method.
   */
  @PostMapping(path = EDIT_URL, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @PreAuthorize(ADMIN_ROLE)
  public String postUserEdit(
          Authentication authentication, final Model model, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes, final UserRequest userRequest) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    CompleteUser user = null;
    if (adminUserService.exist(id)) {
      user = adminUserService.getById(id);
    } else {
      String messageFail = "Ilyen ID = " + id + " -val nem található felhasználó!";
      redirectAttributes.addFlashAttribute(SUCCESS, false);
      redirectAttributes.addFlashAttribute(MESSAGE_LINE_ONE, messageFail);
      return REDIRECT_ADMIN_USER_LIST;
    }

    adminUserService.save(userRequest, user);


    redirectAttributes.addFlashAttribute(SUCCESS, true);
    redirectAttributes.addFlashAttribute(MESSAGE_LINE_ONE, MESSAGE_SUCCESS);

    return REDIRECT + "/admin/user/" + id + "/edit";
  }


  /**
   * Admin user remove user.
   */
  @GetMapping(path = REMOVE_URL)
  @PreAuthorize(ADMIN_ROLE)
  public String getRemoveUser(
          Authentication authentication, final Model model, final @PathVariable("id") Long id) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    adminUserService.deleteById(id);

    return REDIRECT_ADMIN_USER_LIST;
  }

}
