package brblnt.icms.web.controller.modules.worksheet;

import static brblnt.icms.service.utilities.Utilities.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.modules.worksheet.exporter.CreateWorksheetPDF;
import brblnt.icms.service.service.modules.common.CommonColleagueService;
import brblnt.icms.service.service.modules.common.CommonCompanyService;
import brblnt.icms.service.service.modules.worksheet.*;
import brblnt.icms.service.utilities.Utilities;
import brblnt.icms.web.model.modules.worksheet.worksheet.request.WorksheetCreateRequest;
import brblnt.icms.web.model.modules.worksheet.worksheet.request.WorksheetRequest;
import com.lowagie.text.DocumentException;
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
 * Main worksheet controller.
 * '/worksheet'.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = WORKSHEET_URL)
public class WorksheetMainController {

  private final Utilities utilities;
  private final WorksheetFaultService worksheetFaultService;
  private final WorksheetCustomerService worksheetCustomerService;
  private final WorksheetProductService worksheetProductService;
  private final WorksheetServicesService worksheetServicesService;
  private final WorksheetObjectService worksheetObjectService;
  private final WorksheetService worksheetService;
  private final CommonColleagueService commonColleagueService;
  private final CommonCompanyService commonCompanyService;

  /**
   * Get worksheet list.
   */
  @GetMapping(path = LIST_URL)
  public String getWorksheets(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(ROLE_FOR_CONTENT, utilities.content(authentication));
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(MODEL_CONTENT, WORKSHEET_MODULE);
    model.addAttribute(WORKSHEET_CONTENT, LIST_CONTENT);
    model.addAttribute(WORKSHEET_MODEL_WORKSHEETS, worksheetService.getAll());


    return INDEX;
  }

  /**
   * Get worksheet fragment by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public String getWorksheet(
          Authentication authentication, final Model model, @PathVariable("id") Long worksheetID) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    if (worksheetID != -1) {
      model.addAttribute(WORKSHEET_MODEL_ACTUAL_WORKSHEET, worksheetService.getById(worksheetID));

      model.addAttribute(WORKSHEET_MODEL_FAULTS, worksheetFaultService.getAll());
      model.addAttribute(WORKSHEET_MODEL_PRODUCTS, worksheetProductService.getAll());
      model.addAttribute(WORKSHEET_MODEL_SERVICES, worksheetServicesService.getAll());
      model.addAttribute(WORKSHEET_MODEL_WORKERS, commonColleagueService.getAll());
    } else {
      return "modules/worksheet/list/worksheet-list-fragment-empty :: empty";
    }

    return "modules/worksheet/list/worksheet-list-fragment :: worksheet";
  }


  /**
   * Delete worksheet.
   */
  @GetMapping(path = DELETE_URL)
  public String getWorksheetDelete(
          Authentication authentication, @PathVariable("id") Long worksheetID,
          final RedirectAttributes redirectAttributes) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }
    boolean success = false;
    if (worksheetID > 1) {
      success = worksheetService.deleteById(worksheetID);
    }

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_DELETE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_DELETE);
    }


    return REDIRECT_WORKSHEET_MAIN;
  }

  /**
   * Get new worksheet create.
   */
  @GetMapping(path = NEW_URL)
  public String getNew(
          Authentication authentication, final Model model) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(ROLE_FOR_CONTENT, utilities.content(authentication));
    model.addAttribute(MODEL_TOPBAR, actualUser);
    model.addAttribute(MODEL_CONTENT, WORKSHEET_MODULE);
    model.addAttribute(WORKSHEET_CONTENT, NEW_CONTENT);


    model.addAttribute(WORKSHEET_MODEL_ALL_CUSTOMERS, worksheetCustomerService.getAll());
    model.addAttribute(WORKSHEET_MODEL_ALL_FAULTS, worksheetFaultService.getAll());


    return INDEX;
  }

  /**
   * Create new worksheet with post method.
   */
  @PostMapping(path = NEW_URL, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String postNew(
          Authentication authentication, final WorksheetCreateRequest worksheetCreateRequest) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    worksheetService.createNew(worksheetCreateRequest);

    return REDIRECT_WORKSHEET_MAIN;
  }


  /**
   * Get product fragment for create new worksheet.
   */
  @GetMapping(path = "/new/customer/select/{id}")
  public String getNewItems(
          Authentication authentication, final Model model, @PathVariable("id") Long customerID) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }

    model.addAttribute(WORKSHEET_MODEL_ITEMS, worksheetObjectService.getCustomerItems(customerID));
    model.addAttribute(WORKSHEET_MODEL_ALL_FAULTS, worksheetFaultService.getAll());

    return "modules/worksheet/worksheet-new :: itemsForCustomer";
  }


  /**
   * Post worksheet edit.
   */
  @PostMapping(path = "/{worksheetId}/edit")
  public String getEditWorksheet(
          Authentication authentication, @PathVariable("worksheetId") Long worksheetId,
          final RedirectAttributes redirectAttributes, final WorksheetRequest worksheetRequest) {

    CompleteUser actualUser = utilities.getApplicationUser(authentication);
    if (actualUser.getUserName().equals(PASSWORD_EXPIRE_NAME)) {
      return PASSWORD_EXPIRED_REDIRECT;
    }


    boolean success = worksheetService.save(worksheetRequest, worksheetService.getById(worksheetId));

    if (success) {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_SUCCESSFUL_UPDATE);
    } else {
      redirectAttributes.addFlashAttribute(TOAST_INVOKE, TOAST_UNSUCCESSFUL_UPDATE);
    }

    return REDIRECT_WORKSHEET_MAIN;
  }




  /**
   * Export worksheet by id.
   */
  @GetMapping("/{worksheetId}/export")
  public void exportToPdf(HttpServletResponse response,  @PathVariable("worksheetId") Long worksheetId)
          throws DocumentException, IOException {

    response.setContentType("application/pdf");
    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String currentDateTime = dateFormatter.format(new Date());

    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=worksheet_" + currentDateTime + ".pdf";
    response.setHeader(headerKey, headerValue);

    CreateWorksheetPDF exporter = new CreateWorksheetPDF();
    exporter.setCompany(commonCompanyService.getById(0L));
    exporter.setWorksheet(worksheetService.getById(worksheetId));
    exporter.setColleague(commonColleagueService.getById(worksheetService.getById(worksheetId).getEngineerCode()));
    DateFormat dateFormatterYear = new SimpleDateFormat("yyyy");
    exporter.setYear(dateFormatterYear.format(new Date()));
    exporter.export(response);
  }
}
