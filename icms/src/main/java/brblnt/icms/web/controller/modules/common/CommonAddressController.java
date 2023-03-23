package brblnt.icms.web.controller.modules.common;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.service.modules.common.CommonAddressService;
import brblnt.icms.service.service.modules.common.CommonCompanyService;
import brblnt.icms.service.service.modules.worksheet.WorksheetCustomerService;
import brblnt.icms.service.service.modules.worksheet.WorksheetDealerService;
import brblnt.icms.web.model.modules.common.request.AddressRequest;
import brblnt.icms.web.model.modules.common.request.IdRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Common address controller.
 * '/address'.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = ADDRESS_URL)
public class CommonAddressController {

  private final CommonAddressService commonAddressService;
  private final CommonCompanyService commonCompanyService;
  private final WorksheetCustomerService worksheetCustomerService;
  private final WorksheetDealerService worksheetDealerService;


  /**
   * Edit company address.
   */
  @PostMapping(path = "/company/{id}/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String postCompanyEdit(
          final Model model, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes, final AddressRequest addressRequest, final IdRequest idRequest) {

    boolean success = true;

    try {
      if (id == 0) {
        success = commonCompanyService.updateCompanyAddress(
                (long) commonAddressService.saveAddress(addressRequest, id), idRequest.getPosition());
      } else {
        commonAddressService.saveAddress(addressRequest, id);
      }
    } catch (Exception e) {
      success = false;
    }

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_UPDATE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
    }

    return REDIRECT_SUPER_USER_BASICS;
  }

  /**
   * Clear company address.
   */
  @GetMapping(path = "/company/clear/{position}")
  public String getAddressClearCompany(
          final @PathVariable("position") Long position, final RedirectAttributes redirectAttributes) {

    boolean success = commonCompanyService.clearAddress(Math.toIntExact(position));

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_UPDATE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
    }

    return REDIRECT_SUPER_USER_BASICS;
  }

  /**
   * Edit dealer address.
   */
  @PostMapping(path = "/dealer/{id}/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String postDealerEdit(
          final Model model, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes, final AddressRequest addressRequest, final IdRequest idRequest) {

    boolean success = false;
    try {
      if (id == 0 && idRequest.getId() != 0) {
        success = worksheetDealerService.updateDealerAddress(idRequest.getId(),
                (long) commonAddressService.saveAddress(addressRequest, id), idRequest.getPosition());

      } else {
        commonAddressService.saveAddress(addressRequest, id);
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
   * Clear dealer address.
   */
  @GetMapping(path = "/dealer/clear/{dealerID}/{position}")
  public String getAddressClearDealer(
          final @PathVariable("dealerID") Long dealerID,
          final @PathVariable("position") Long position, final RedirectAttributes redirectAttributes) {


    boolean success = worksheetDealerService.clearAddressId(dealerID, Math.toIntExact(position));

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_UPDATE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
    }

    return REDIRECT_WORKSHEET_DEALER;
  }

  /**
   * Edit customer address.
   */
  @PostMapping(path = "/customer/{id}/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String postCustomerEdit(
          final Model model, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes, final AddressRequest addressRequest,
          final IdRequest idRequest) {

    boolean success = true;
    try {
      if (id == 0 && idRequest.getId() != 0) {
        success = worksheetCustomerService.updateCustomerAddress(idRequest.getId(),
                (long) commonAddressService.saveAddress(addressRequest, id), idRequest.getPosition());

      } else {
        commonAddressService.saveAddress(addressRequest, id);
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
   * Clear customer address.
   */
  @GetMapping(path = "/customer/clear/{customerID}/{position}")
  public String getAddressClearCustomer(
          final @PathVariable("customerID") Long customerID,
          final @PathVariable("position") Long position, final RedirectAttributes redirectAttributes) {


    boolean success = worksheetCustomerService.clearAddressId(customerID, Math.toIntExact(position));

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_UPDATE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
    }

    return REDIRECT_WORKSHEET_CUSTOMER;
  }

}
