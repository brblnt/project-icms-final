package brblnt.icms.web.controller.modules.worksheet;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.service.modules.worksheet.WorksheetDealerService;
import brblnt.icms.service.service.modules.worksheet.WorksheetProductService;
import brblnt.icms.service.utilities.Utilities;
import brblnt.icms.web.model.modules.worksheet.dealer.request.DealerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *  Worksheet Dealer controller.
 * '/worksheet/dealers'.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = WORKSHEET_DEALER_URL)
public class WorksheetDealerController {

  private final WorksheetDealerService worksheetDealerService;
  private final WorksheetProductService worksheetProductService;
  private final Utilities utilities;

  /**
   * Get all dealers.
   */
  @GetMapping(path = ROOT_URL)
  public String getDealers(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(WORKSHEET_MODEL_DEALERS, worksheetDealerService.getAll());
    model.addAttribute(ROLE_FOR_CONTENT, utilities.content(authentication));
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(MODEL_CONTENT, WORKSHEET_MODULE);
    model.addAttribute(WORKSHEET_CONTENT, WORKSHEET_DEALERS_CONTENT);

    return INDEX;
  }

  /**
   * Get dealer fragment by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public String getDealerByID(
          Authentication authentication, final Model model, @PathVariable("id") Long dealerID) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    if (dealerID != -1) {
      model.addAttribute(WORKSHEET_MODEL_ACTUAL_DEALER,
              worksheetDealerService.getById(dealerID));
    } else {
      return "modules/worksheet/dealer/worksheet-dealer-fragment-empty :: empty";
    }

    return "modules/worksheet/dealer/worksheet-dealer-fragment :: dealer";
  }

  /**
   * Get dealer edit.
   */
  @GetMapping(path = EDIT_URL)
  public String getDealerEdit(
          Authentication authentication, final @PathVariable("id") Long id) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    return REDIRECT_WORKSHEET_DEALER;
  }

  /**
   * Edit dealer with post method.
   */
  @PostMapping(path = EDIT_URL, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String postDealerEdit(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes, final DealerRequest dealerRequest) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    if (dealerRequest.getDealerName().equals("")
            || dealerRequest.getEmailAddress().equals("")
            || dealerRequest.getPhoneNumber().equals("")) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
      return REDIRECT_WORKSHEET_DEALER;
    }

    boolean success = false;
    try {
      if (id == 0) {
        success = worksheetDealerService.save(dealerRequest, worksheetDealerService.createEmpty());
      } else {
        success = worksheetDealerService.save(dealerRequest, worksheetDealerService.getById(id));
      }
    } catch (Exception e) {
      success = false;
    }


    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_UPDATE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
    }

    return REDIRECT_WORKSHEET_DEALER;
  }


  /**
   * Delete dealer.
   */
  @GetMapping(path = DELETE_URL)
  public String getDealerDelete(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    if (id == 0) {
      return REDIRECT_WORKSHEET_DEALER;
    }
    worksheetProductService.clearUsage(id);
    boolean success = worksheetDealerService.deleteById(id);

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_DELETE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_DELETE);
    }

    return REDIRECT_WORKSHEET_DEALER;
  }

  /**
   * Create dealer.
   */
  @GetMapping(path = CREATE_URL)
  public String getDealerCreate(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(WORKSHEET_MODEL_ACTUAL_DEALER, worksheetDealerService.createEmpty());

    return "modules/worksheet/dealer/worksheet-dealer-fragment :: dealer";

  }


}
