package brblnt.icms.web.controller.modules.worksheet.rest;

import static brblnt.icms.service.utilities.Utilities.*;

import java.util.List;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteCustomer;
import brblnt.icms.service.service.modules.worksheet.WorksheetCustomerService;
import brblnt.icms.service.service.modules.worksheet.WorksheetObjectService;
import brblnt.icms.web.model.modules.worksheet.customer.request.CustomerRequest;
import brblnt.icms.web.model.modules.worksheet.customer.response.CustomerResponse;
import brblnt.icms.web.model.modules.worksheet.object.request.ObjectRequest;
import brblnt.icms.web.model.modules.worksheet.object.response.ObjectResponse;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Customer Rest Controller.
 * '/api/worksheet/customers'.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = API + WORKSHEET_CUSTOMER_URL)
public class WorksheetCustomerRestController {

  private final WorksheetCustomerService worksheetCustomerService;
  private final WorksheetObjectService worksheetObjectService;
  private final Converter<CompleteCustomer, CustomerResponse> completeCustomerCustomerResponseConverter;

  /**
   * Get customer list.
   */
  @GetMapping(path = ROOT_URL)
  public List<CustomerResponse> getCustomers() {
    return worksheetCustomerService.getAll().stream()
            .map(completeCustomerCustomerResponseConverter::convert)
            .toList();
  }

  /**
   * Get customer by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public CustomerResponse getCustomer(final @PathVariable Long id) {
    return completeCustomerCustomerResponseConverter.convert(worksheetCustomerService.getById(id));
  }

  /**
   * Create customer.
   */
  @PostMapping
  public CustomerResponse createCustomer(final @RequestBody CustomerRequest customerRequest) {
    worksheetCustomerService.save(customerRequest, worksheetCustomerService.createEmpty());
    return completeCustomerCustomerResponseConverter.convert(
            worksheetCustomerService.create(customerRequest));
  }

  /**
   * Update customer.
   */
  @PutMapping(path = ID_PATH_VARIABLE)
  public CustomerResponse updateCustomer(final @PathVariable Long id, final @RequestBody CustomerRequest customerRequest) {
    worksheetCustomerService.save(customerRequest, worksheetCustomerService.getById(id));
    return completeCustomerCustomerResponseConverter.convert(worksheetCustomerService.getById(id));
  }

  /**
   * Delete customer.
   */
  @DeleteMapping(path = ID_PATH_VARIABLE)
  public ResponseEntity<Void> deleteCustomer(final @PathVariable Long id) {
    worksheetObjectService.clearUsage(id);
    worksheetCustomerService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
