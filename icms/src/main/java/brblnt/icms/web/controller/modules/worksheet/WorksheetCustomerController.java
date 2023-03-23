package brblnt.icms.web.controller.modules.worksheet;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.service.modules.worksheet.WorksheetCustomerService;
import brblnt.icms.service.service.modules.worksheet.WorksheetObjectService;
import brblnt.icms.service.utilities.Utilities;
import brblnt.icms.web.model.modules.worksheet.customer.request.CustomerRequest;
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
 * Worksheet Customer Controller.
 * '/worksheet/customers'.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = WORKSHEET_CUSTOMER_URL)
public class WorksheetCustomerController {

  private final WorksheetCustomerService worksheetCustomerService;
  private final WorksheetObjectService worksheetObjectService;
  private final Utilities utilities;


  /**
   * Get customer list.
   */
  @GetMapping(path = ROOT_URL)
  public String getCustomers(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(WORKSHEET_MODEL_CUSTOMERS, worksheetCustomerService.getAll());
    model.addAttribute(ROLE_FOR_CONTENT, utilities.content(authentication));
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(MODEL_CONTENT, WORKSHEET_MODULE);
    model.addAttribute(WORKSHEET_CONTENT, WORKSHEET_CUSTOMER_CONTENT);

    return INDEX;
  }

  /**
   * Get customer fragment by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public String getCustomerByID(
          Authentication authentication, final Model model, @PathVariable("id") Long customerID) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    if (customerID != -1) {
      model.addAttribute(WORKSHEET_MODEL_ACTUAL_CUSTOMER,
              worksheetCustomerService.getById(customerID));
    } else {
      return "modules/worksheet/customer/worksheet-customer-fragment-empty :: empty";
    }

    return "modules/worksheet/customer/worksheet-customer-fragment :: customer";
  }

  /**
   * Get edit customer.
   */
  @GetMapping(path = EDIT_URL)
  public String getCustomerEdit(
          Authentication authentication, final @PathVariable("id") Long id) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    return REDIRECT_WORKSHEET_CUSTOMER;
  }

  /**
   * Edit customer with post method.
   */
  @PostMapping(path = EDIT_URL, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String postCustomerEdit(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes, final CustomerRequest customerRequest) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    if (customerRequest.getCustomerName().equals("")
            || customerRequest.getEmailAddress().equals("")
            || customerRequest.getPhoneNumber().equals("")) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
      return REDIRECT_WORKSHEET_CUSTOMER;
    }

    boolean success = false;
    try {
      if (id == 0) {
        customerRequest.setRegistrationDate("na");
        success = worksheetCustomerService.save(customerRequest, worksheetCustomerService.createEmpty());
      } else {
        success = worksheetCustomerService.save(customerRequest, worksheetCustomerService.getById(id));
      }
    } catch (Exception e) {
      success = false;
    }

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_UPDATE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
    }

    return REDIRECT_WORKSHEET_CUSTOMER;
  }


  /**
   * Delete customer.
   */
  @GetMapping(path = DELETE_URL)
  public String getCustomerDelete(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    if (id == 0) {
      return REDIRECT_WORKSHEET_CUSTOMER;
    }
    worksheetObjectService.clearUsage(id);
    boolean success = worksheetCustomerService.deleteById(id);

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_DELETE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_DELETE);
    }

    return REDIRECT_WORKSHEET_CUSTOMER;
  }

  /**
   * Create customer.
   */
  @GetMapping(path = CREATE_URL)
  public String getCustomerCreate(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(WORKSHEET_MODEL_ACTUAL_CUSTOMER, worksheetCustomerService.createEmpty());

    return "modules/worksheet/customer/worksheet-customer-fragment :: customer";

  }

}
