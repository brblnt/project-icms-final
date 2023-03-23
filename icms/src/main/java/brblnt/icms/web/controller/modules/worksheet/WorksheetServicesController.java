package brblnt.icms.web.controller.modules.worksheet;

import static brblnt.icms.service.utilities.Utilities.*;

import java.util.List;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.modules.worksheet.converter.creator.CompleteServiceCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteService;
import brblnt.icms.service.service.modules.worksheet.WorksheetServicesService;
import brblnt.icms.service.utilities.Utilities;
import brblnt.icms.web.model.modules.worksheet.fault.request.FaultRequest;
import brblnt.icms.web.model.modules.worksheet.services.request.ServiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Worksheet Services Controller.
 * '/worksheet/services'.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = WORKSHEET_SERVICES_URL)
public class WorksheetServicesController {

  private final WorksheetServicesService worksheetServicesService;
  private final Utilities utilities;


  /**
   * Get all services.
   */
  @GetMapping(path = ROOT_URL)
  public String getServices(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    List<CompleteService> list = worksheetServicesService.getAll();

    model.addAttribute(WORKSHEET_MODEL_SERVICES, list);
    model.addAttribute(ROLE_FOR_CONTENT, utilities.content(authentication));
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(MODEL_CONTENT, WORKSHEET_MODULE);
    model.addAttribute(WORKSHEET_CONTENT, WORKSHEET_SERVICES_CONTENT);

    return INDEX;
  }

  /**
   * Get service fragment by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public String getServicesByID(
          Authentication authentication, final Model model, @PathVariable("id") Long serviceID) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    if (serviceID != -1) {
      CompleteService actualService = worksheetServicesService.getById(serviceID);
      model.addAttribute(WORKSHEET_MODEL_ACTUAL_SERVICE, actualService);
    } else {
      return "modules/worksheet/service/worksheet-service-fragment-empty :: empty";
    }

    return "modules/worksheet/service/worksheet-service-fragment :: service";
  }

  /**
   * Edit service.
   */
  @GetMapping(path = EDIT_URL)
  public String getServicesEdit(
          Authentication authentication, final @PathVariable("id") Long id) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    return REDIRECT_WORKSHEET_SERVICE;
  }

  /**
   * Edit service with post method.
   */
  @PostMapping(path = EDIT_URL, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String postServicesEdit(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes, final ServiceRequest serviceRequest) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    if (worksheetServicesService.exist(serviceRequest.getCustomCode(),
            id != 0 ? worksheetServicesService.getById(id).getCustomCode() : "")) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_CUSTOM_CODE_NOT_UNIQUE);
      return REDIRECT_WORKSHEET_SERVICE;
    }

    if (serviceRequest.getServiceName().equals("") ||
            serviceRequest.getPriceN() < 0 || serviceRequest.getPriceB() < 0 || serviceRequest.getVat() < 0) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
      return REDIRECT_WORKSHEET_SERVICE;
    }

    boolean success = false;
    try {
      success = worksheetServicesService.save(serviceRequest,
              id == 0 ? worksheetServicesService.createEmpty() : worksheetServicesService.getById(id));
    } catch (Exception e) {
      success = false;
    }

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_UPDATE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
    }

    return REDIRECT_WORKSHEET_SERVICE;
  }

  /**
   * Service delete.
   */
  @GetMapping(path = DELETE_URL)
  public String getServicesDelete(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    if (id == 0) {
      return REDIRECT_WORKSHEET_SERVICE;
    }

    boolean success = worksheetServicesService.deleteById(id);

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_DELETE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_DELETE);
    }

    return REDIRECT_WORKSHEET_SERVICE;
  }

  /**
   * Service create page.
   */
  @GetMapping(path = CREATE_URL)
  public String getServicesCreate(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(WORKSHEET_MODEL_ACTUAL_SERVICE, worksheetServicesService.createEmpty());

    return "modules/worksheet/service/worksheet-service-fragment :: service";
  }

}
