package brblnt.icms.web.controller.modules.worksheet.rest;

import static brblnt.icms.service.utilities.Utilities.*;

import java.util.List;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteService;
import brblnt.icms.service.service.modules.worksheet.WorksheetServicesService;
import brblnt.icms.web.model.modules.worksheet.services.request.ServiceRequest;
import brblnt.icms.web.model.modules.worksheet.services.response.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Worksheet Rest Controller.
 * '/api/worksheet/services'.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = API + WORKSHEET_SERVICES_URL)
public class WorksheetServicesRestController {

  private final WorksheetServicesService worksheetServicesService;
  private final Converter<CompleteService, ServiceResponse> completeServiceServiceResponseConverter;

  /**
   * Get services list.
   */
  @GetMapping(path = ROOT_URL)
  public List<ServiceResponse> getServices() {
    return worksheetServicesService.getAll().stream()
            .map(completeServiceServiceResponseConverter::convert)
            .toList();
  }

  /**
   * Get service by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public ServiceResponse getService(final @PathVariable Long id) {
    return completeServiceServiceResponseConverter.convert(worksheetServicesService.getById(id));
  }

  /**
   * Create service.
   */
  @PostMapping
  public ServiceResponse createService(final @RequestBody ServiceRequest serviceRequest) {
    if (worksheetServicesService.exist(serviceRequest.getCustomCode(), "")) {
      return null;
    }
    worksheetServicesService.save(serviceRequest, worksheetServicesService.createEmpty());
    return completeServiceServiceResponseConverter.convert(
            worksheetServicesService.create(serviceRequest));
  }

  /**
   * Update service.
   */
  @PutMapping(path = ID_PATH_VARIABLE)
  public ServiceResponse updateService(final @PathVariable Long id, final @RequestBody ServiceRequest serviceRequest) {
    if (worksheetServicesService.exist(serviceRequest.getCustomCode(), worksheetServicesService.getById(id).getCustomCode())) {
      return null;
    }
    worksheetServicesService.save(serviceRequest, worksheetServicesService.getById(id));
    return completeServiceServiceResponseConverter.convert(worksheetServicesService.getById(id));
  }

  /**
   * Delete service.
   */
  @DeleteMapping(path = ID_PATH_VARIABLE)
  public ResponseEntity<Void> deleteService(final @PathVariable Long id) {
    worksheetServicesService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
