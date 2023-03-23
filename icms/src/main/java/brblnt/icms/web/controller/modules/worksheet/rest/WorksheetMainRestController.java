package brblnt.icms.web.controller.modules.worksheet.rest;

import static brblnt.icms.service.utilities.Utilities.*;
import static brblnt.icms.service.utilities.Utilities.ID_PATH_VARIABLE;

import java.util.List;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteWorksheet;
import brblnt.icms.service.service.modules.common.CommonColleagueService;
import brblnt.icms.service.service.modules.worksheet.*;
import brblnt.icms.web.model.modules.worksheet.customer.request.CustomerRequest;
import brblnt.icms.web.model.modules.worksheet.customer.response.CustomerResponse;
import brblnt.icms.web.model.modules.worksheet.worksheet.request.WorksheetCreateRequest;
import brblnt.icms.web.model.modules.worksheet.worksheet.request.WorksheetRequest;
import brblnt.icms.web.model.modules.worksheet.worksheet.response.WorksheetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Worksheet Rest Controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = API + WORKSHEET_URL)
public class WorksheetMainRestController {
  private final WorksheetService worksheetService;
  private final Converter<CompleteWorksheet, WorksheetResponse> completeWorksheetWorksheetResponseConverter;

  /**
   * Get all worksheet.
   */
  @GetMapping(path = ROOT_URL)
  public List<WorksheetResponse> getWorksheets() {
    return worksheetService.getAll().stream()
            .map(completeWorksheetWorksheetResponseConverter::convert)
            .toList();
  }

  /**
   * Get worksheet by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public WorksheetResponse getWorksheet(final @PathVariable Long id) {
    return completeWorksheetWorksheetResponseConverter.convert(worksheetService.getById(id));
  }

  /**
   * Create worksheet.
   */
  @PostMapping
  public WorksheetResponse createWorksheet(final @RequestBody WorksheetCreateRequest worksheetCreateRequest) {
    return completeWorksheetWorksheetResponseConverter.convert(worksheetService.createNew(worksheetCreateRequest));
  }

  /**
   * Update worksheet.
   */
  @PutMapping(path = ID_PATH_VARIABLE)
  public WorksheetResponse updateWorksheet(final @PathVariable Long id, final @RequestBody WorksheetRequest worksheetRequest) {
    worksheetService.save(worksheetRequest, worksheetService.getById(id));
    return completeWorksheetWorksheetResponseConverter.convert(worksheetService.getById(id));
  }

  /**
   * Delete worksheet.
   */
  @DeleteMapping(path = ID_PATH_VARIABLE)
  public ResponseEntity<Void> deleteWorksheet(final @PathVariable Long id) {
    worksheetService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
