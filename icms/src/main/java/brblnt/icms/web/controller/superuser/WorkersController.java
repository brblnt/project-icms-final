package brblnt.icms.web.controller.superuser;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.common.model.complete.CompleteColleague;
import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.service.modules.common.CommonColleagueService;
import brblnt.icms.service.utilities.Utilities;
import brblnt.icms.web.model.modules.common.request.ColleagueRequest;
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
 * Super-user workers controller.
 * '/super-user/settings'.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = SUPER_USER_WORKERS)
public class WorkersController {

  private final Utilities utilities;
  private final CommonColleagueService commonColleagueService;

  /**
   * Get worker list.
   */
  @GetMapping(path = ROOT_URL)
  @PreAuthorize(SUPER_USER_ROLE)
  public String getWorkers(Authentication authentication, final Model model) {
    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    model.addAttribute(ROLE_FOR_CONTENT, SUPER_USER);
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(SUPER_USER_CONTENT, SUPER_USER_WORKERS_CONTENT);
    model.addAttribute(SUPER_USER_WORKERS_CONTENT, commonColleagueService.getAll());
    return INDEX;
  }

  /**
   * Get worker fragment.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  @PreAuthorize(SUPER_USER_ROLE)
  public String getWorkersByID(
          Authentication authentication, final Model model, @PathVariable("id") Long workerID) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    if (workerID != -1) {
      CompleteColleague actualColleague = commonColleagueService.getById(workerID);
      model.addAttribute(SUPER_USER_ACTUAL_WORKER, actualColleague);
    } else {
      return "super-user-pages/settings/workers/workers-fragment-empty :: empty";
    }

    return "super-user-pages/settings/workers/workers-fragment :: workers";
  }

  /**
   * Edit worker.
   */
  @GetMapping(path = EDIT_URL)
  public String getWorkersEdit(
          Authentication authentication, final @PathVariable("id") Long id) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    return REDIRECT_SUPER_USER_WORKERS;
  }

  /**
   * Edit worker with put method.
   */
  @PostMapping(path = EDIT_URL, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String postWorkersEdit(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes, final ColleagueRequest colleagueRequest) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    if (commonColleagueService.exist(colleagueRequest.getCustomCode(), commonColleagueService.getById(id).getCustomCode())) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_CUSTOM_CODE_NOT_UNIQUE);
      return REDIRECT_SUPER_USER_WORKERS;
    }
    if (colleagueRequest.getCustomCode().equals("") || colleagueRequest.getName().equals("")
            || colleagueRequest.getPhoneNumber().equals("")) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
      return REDIRECT_SUPER_USER_WORKERS;
    }

    boolean success = commonColleagueService.save(colleagueRequest, commonColleagueService.getById(id));

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_UPDATE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
    }

    return REDIRECT_SUPER_USER_WORKERS;
  }

  /**
   * Delete worker.
   */
  @GetMapping(path = DELETE_URL)
  public String getWorkersDelete(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    if (id == 0) {
      return REDIRECT_SUPER_USER_WORKERS;
    }

    boolean success = commonColleagueService.deleteById(id);

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_DELETE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_DELETE);
    }

    return REDIRECT_SUPER_USER_WORKERS;
  }

  /**
   * Get create form for workers.
   */
  @GetMapping(path = CREATE_URL)
  public String getWorkersCreate(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(SUPER_USER_ACTUAL_WORKER, commonColleagueService.createEmpty());

    return "super-user-pages/settings/workers/workers-fragment :: workers";
  }

}
