package brblnt.icms.web.controller.modules.worksheet;

import static brblnt.icms.service.utilities.Utilities.*;

import java.util.List;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.modules.worksheet.converter.creator.CompleteFaultCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteCustomer;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteFault;
import brblnt.icms.service.service.modules.worksheet.WorksheetFaultService;
import brblnt.icms.service.utilities.Utilities;
import brblnt.icms.web.model.modules.worksheet.customer.request.CustomerRequest;
import brblnt.icms.web.model.modules.worksheet.fault.request.FaultRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Worksheet fault controller.
 * '/worksheet/fault'.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = WORKSHEET_FAULT_URL)
public class WorksheetFaultController {

  private final WorksheetFaultService worksheetFaultService;
  private final Utilities utilities;

  /**
   * Get fault list.
   */
  @GetMapping(path = ROOT_URL)
  public String getFaults(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    List<CompleteFault> list = worksheetFaultService.getAll();


    model.addAttribute(WORKSHEET_MODEL_FAULTS, list);
    model.addAttribute(ROLE_FOR_CONTENT, utilities.content(authentication));
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(MODEL_CONTENT, WORKSHEET_MODULE);
    model.addAttribute(WORKSHEET_CONTENT, WORKSHEET_FAULTS_CONTENT);
    return INDEX;
  }

  /**
   * Get fault fragment by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public String getFaultByID(
          Authentication authentication, final Model model, @PathVariable("id") Long faultID) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    if (faultID != -1) {
      CompleteFault actualFault = worksheetFaultService.getById(faultID);
      model.addAttribute(WORKSHEET_MODEL_ACTUAL_FAULT, actualFault);
    } else {
      return "modules/worksheet/fault/worksheet-fault-fragment-empty :: empty";
    }

    return "modules/worksheet/fault/worksheet-fault-fragment :: fault";
  }

  /**
   * Edit fault.
   */
  @GetMapping(path = EDIT_URL)
  public String getFaultEdit(
          Authentication authentication, final @PathVariable("id") Long id) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    return REDIRECT_WORKSHEET_FAULT;
  }


  /**
   * Edit fault with post method.
   */
  @PostMapping(path = EDIT_URL, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String postFaultEdit(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes, final FaultRequest faultRequest) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    if (faultRequest.getFaultName().equals("")) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_UPDATE);
      return REDIRECT_WORKSHEET_FAULT;
    }

    boolean success = worksheetFaultService.save(faultRequest,
            id == 0 ? worksheetFaultService.createEmpty() : worksheetFaultService.getById(id));;

    try {
      if (success) {
        redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_UPDATE);
      } else {
        redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
      }
    } catch (Exception e) {
      success = false;
    }

    return REDIRECT_WORKSHEET_FAULT;
  }

  /**
   * Delete fault.
   */
  @GetMapping(path = DELETE_URL)
  public String getFaultDelete(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    if (id == 0) {
      return REDIRECT_WORKSHEET_FAULT;
    }
    boolean success = worksheetFaultService.deleteById(id);

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_DELETE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_DELETE);
    }

    return REDIRECT_WORKSHEET_FAULT;
  }

  /**
   * Create fault.
   */
  @GetMapping(path = CREATE_URL)
  public String getFaultCreate(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(WORKSHEET_MODEL_ACTUAL_FAULT, worksheetFaultService.createEmpty());

    return "modules/worksheet/fault/worksheet-fault-fragment :: fault";
  }

}
