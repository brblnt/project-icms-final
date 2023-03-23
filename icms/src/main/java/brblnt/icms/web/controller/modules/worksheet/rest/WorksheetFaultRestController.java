package brblnt.icms.web.controller.modules.worksheet.rest;

import static brblnt.icms.service.utilities.Utilities.*;

import java.util.List;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteFault;
import brblnt.icms.service.service.modules.worksheet.WorksheetFaultService;
import brblnt.icms.web.model.modules.worksheet.fault.request.FaultRequest;
import brblnt.icms.web.model.modules.worksheet.fault.response.FaultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Fault Rest Controller.
 * '/api/worksheet/fault'.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = API + WORKSHEET_FAULT_URL)
public class WorksheetFaultRestController {

  private final WorksheetFaultService worksheetFaultService;
  private final Converter<CompleteFault, FaultResponse> completeFaultFaultResponseConverter;

  /**
   * Get fault list.
   */
  @GetMapping(path = ROOT_URL)
  public List<FaultResponse> getFaults() {
    return worksheetFaultService.getAll().stream()
            .map(completeFaultFaultResponseConverter::convert)
            .toList();
  }

  /**
   * Get fault by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public FaultResponse getFault(final @PathVariable Long id) {
    return completeFaultFaultResponseConverter.convert(worksheetFaultService.getById(id));
  }

  /**
   * Create fault.
   */
  @PostMapping
  public FaultResponse createFault(final @RequestBody FaultRequest faultRequest) {
    worksheetFaultService.save(faultRequest, worksheetFaultService.createEmpty());
    return completeFaultFaultResponseConverter.convert(
            worksheetFaultService.create(faultRequest));
  }

  /**
   * Update fault.
   */
  @PutMapping(path = ID_PATH_VARIABLE)
  public FaultResponse updateFault(final @PathVariable Long id, final @RequestBody FaultRequest faultRequest) {
    worksheetFaultService.save(faultRequest, worksheetFaultService.getById(id));
    return completeFaultFaultResponseConverter.convert(worksheetFaultService.getById(id));
  }

  /**
   * Delete fault.
   */
  @DeleteMapping(path = ID_PATH_VARIABLE)
  public ResponseEntity<Void> deleteFault(final @PathVariable Long id) {
    worksheetFaultService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
