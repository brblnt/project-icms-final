package brblnt.icms.web.controller.modules.worksheet;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.service.modules.worksheet.WorksheetProductService;
import brblnt.icms.service.utilities.Utilities;
import brblnt.icms.web.model.modules.worksheet.product.request.ProductRequest;
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
 * Worksheet product controller.
 * '/worksheet/product'.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = WORKSHEET_PRODUCT_URL)
public class WorksheetProductController {

  private final WorksheetProductService worksheetProductService;
  private final Utilities utilities;


  /**
   * Get all product.
   */
  @GetMapping(path = ROOT_URL)
  public String getProducts(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(WORKSHEET_MODEL_PRODUCTS, worksheetProductService.getAll());
    model.addAttribute(ROLE_FOR_CONTENT, utilities.content(authentication));
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(MODEL_CONTENT, WORKSHEET_MODULE);
    model.addAttribute(WORKSHEET_CONTENT, WORKSHEET_PRODUCTS_CONTENT);
    return INDEX;
  }

  /**
   * Get product fragment by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public String getProductByID(
          Authentication authentication, final Model model, @PathVariable("id") Long productID) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    if (productID != -1) {
      model.addAttribute(WORKSHEET_MODEL_PRODUCT_DEALER,
              worksheetProductService.getDealerListWithoutOwner(worksheetProductService.getById(productID).getDealer().getId()));
      model.addAttribute(WORKSHEET_MODEL_ACTUAL_PRODUCT, worksheetProductService.getById(productID));
    } else {
      return "modules/worksheet/product/worksheet-product-fragment-empty :: empty";
    }

    return "modules/worksheet/product/worksheet-product-fragment :: product";
  }

  /**
   * Get edit product.
   */
  @GetMapping(path = EDIT_URL)
  public String getProductEdit(
          Authentication authentication, final @PathVariable("id") Long id) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    return REDIRECT_WORKSHEET_PRODUCT;
  }

  /**
   * Post edit product.
   */
  @PostMapping(path = EDIT_URL, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String postProductEdit(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes, final ProductRequest productRequest) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    if (worksheetProductService.exist(productRequest.getCustomCode(),
            id != 0 ? worksheetProductService.getById(id).getCustomCode() : "")) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_CUSTOM_CODE_NOT_UNIQUE);
      return REDIRECT_WORKSHEET_PRODUCT;
    }

    boolean success = false;
    try {
      if (id == 0) {
        success = worksheetProductService.save(productRequest, worksheetProductService.createEmpty());
      } else {
        success = worksheetProductService.save(productRequest, worksheetProductService.getById(id));
      }
    } catch (Exception e) {
      success = false;
    }


    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_UPDATE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
    }

    return REDIRECT_WORKSHEET_PRODUCT;
  }

  /**
   * Delete product.
   */
  @GetMapping(path = DELETE_URL)
  public String getProductDelete(
          Authentication authentication, final @PathVariable("id") Long id,
          final RedirectAttributes redirectAttributes) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    if (id == 0) {
      return REDIRECT_WORKSHEET_PRODUCT;
    }

    boolean success = worksheetProductService.deleteById(id);

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_DELETE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_DELETE);
    }

    return REDIRECT_WORKSHEET_PRODUCT;
  }

  /**
   * Create product.
   */
  @GetMapping(path = CREATE_URL)
  public String getProductCreate(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(WORKSHEET_MODEL_PRODUCT_DEALER, worksheetProductService.getDealerListWithoutOwner(0L));
    model.addAttribute(WORKSHEET_MODEL_ACTUAL_PRODUCT, worksheetProductService.createEmpty());

    return "modules/worksheet/product/worksheet-product-fragment :: product";

  }

}
