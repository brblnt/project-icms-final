package brblnt.icms.web.controller.modules.worksheet;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.service.modules.worksheet.WorksheetObjectService;
import brblnt.icms.service.utilities.Utilities;
import brblnt.icms.web.model.modules.worksheet.object.request.ObjectRequest;
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
 * Worksheet Object Controller.
 * '/worksheet/object'.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = WORKSHEET_OBJECT_URL)
public class WorksheetObjectController {

  private final WorksheetObjectService worksheetObjectService;
  private final Utilities utilities;

  /**
   * Get all object.
   */
  @GetMapping(path = ROOT_URL)
  public String getObject(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(WORKSHEET_MODEL_OBJECTS, worksheetObjectService.getAll());
    model.addAttribute(ROLE_FOR_CONTENT, utilities.content(authentication));
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(MODEL_CONTENT, WORKSHEET_MODULE);
    model.addAttribute(WORKSHEET_CONTENT, WORKSHEET_OBJECTS_CONTENT);
    return INDEX;
  }

  /**
   * Get object fragment by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public String getObjectByID(
          Authentication authentication, final Model model, @PathVariable("id") Long objectID) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    if (objectID != -1) {
      model.addAttribute(WORKSHEET_MODEL_OBJECT_CUSTOMER,
              worksheetObjectService.getCustomerListWithoutOwner(
                      worksheetObjectService.getById(objectID).getCustomer().getId()));
      model.addAttribute(WORKSHEET_MODEL_ACTUAL_OBJECT, worksheetObjectService.getById(objectID));
    } else {
      return "modules/worksheet/object/worksheet-object-fragment-empty :: empty";
    }

    return "modules/worksheet/object/worksheet-object-fragment :: object";
  }


  /**
   * Get object edit.
   */
  @GetMapping(path = EDIT_URL)
  public String getObjectEdit(
          Authentication authentication, final @PathVariable("id") Long id) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    return REDIRECT_WORKSHEET_OBJECT;
  }

  /**
   * Post object edit.
   */
  @PostMapping(path = EDIT_URL, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String postObjectEdit(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes, final ObjectRequest objectRequest) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    if (false) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
      return REDIRECT_WORKSHEET_OBJECT;
    }

    boolean success = false;


    try {
      if (id == 0) {
        success = worksheetObjectService.save(objectRequest, worksheetObjectService.createEmpty());
      } else {
        success = worksheetObjectService.save(objectRequest, worksheetObjectService.getById(id));
      }
    } catch (Exception e) {
      success = false;
    }

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_UPDATE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
    }

    return REDIRECT_WORKSHEET_OBJECT;
  }

  /**
   * Object delete.
   */
  @GetMapping(path = DELETE_URL)
  public String getObjectDelete(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    if (id == 0) {
      return REDIRECT_WORKSHEET_OBJECT;
    }

    boolean success = worksheetObjectService.deleteById(id);

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_DELETE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_DELETE);
    }

    return REDIRECT_WORKSHEET_OBJECT;
  }

  /**
   * Create object.
   */
  @GetMapping(path = CREATE_URL)
  public String getObjectCreate(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    model.addAttribute(WORKSHEET_MODEL_OBJECT_CUSTOMER, worksheetObjectService.getCustomerListWithoutOwner(0L));
    model.addAttribute(WORKSHEET_MODEL_ACTUAL_OBJECT, worksheetObjectService.createEmpty());

    return "modules/worksheet/object/worksheet-object-fragment :: object";

  }
}
