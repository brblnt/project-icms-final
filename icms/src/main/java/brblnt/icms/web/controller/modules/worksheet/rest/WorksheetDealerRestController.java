package brblnt.icms.web.controller.modules.worksheet.rest;

import static brblnt.icms.service.utilities.Utilities.*;

import java.util.List;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteDealer;
import brblnt.icms.service.service.modules.worksheet.WorksheetDealerService;
import brblnt.icms.service.service.modules.worksheet.WorksheetProductService;
import brblnt.icms.web.model.modules.worksheet.dealer.request.DealerRequest;
import brblnt.icms.web.model.modules.worksheet.dealer.response.DealerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Dealer Rest Controller.
 * '/api/worksheet/dealers'.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = API + WORKSHEET_DEALER_URL)
public class WorksheetDealerRestController {

  private final WorksheetDealerService worksheetDealerService;
  private final WorksheetProductService worksheetProductService;
  private final Converter<CompleteDealer, DealerResponse> completeDealerDealerResponseConverter;

  /**
   * Get dealer list.
   */
  @GetMapping(path = ROOT_URL)
  public List<DealerResponse> getDealers() {
    return worksheetDealerService.getAll().stream()
            .map(completeDealerDealerResponseConverter::convert)
            .toList();
  }

  /**
   * Get dealer by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public DealerResponse getDealer(final @PathVariable Long id) {
    return completeDealerDealerResponseConverter.convert(worksheetDealerService.getById(id));
  }

  /**
   * Create dealer.
   */
  @PostMapping
  public DealerResponse createDealer(final @RequestBody DealerRequest dealerRequest) {
    worksheetDealerService.save(dealerRequest, worksheetDealerService.createEmpty());
    return completeDealerDealerResponseConverter.convert(
            worksheetDealerService.create(dealerRequest));
  }

  /**
   * Update dealer.
   */
  @PutMapping(path = ID_PATH_VARIABLE)
  public DealerResponse updateDealer(final @PathVariable Long id, final @RequestBody DealerRequest dealerRequest) {
    worksheetDealerService.save(dealerRequest, worksheetDealerService.getById(id));
    return completeDealerDealerResponseConverter.convert(worksheetDealerService.getById(id));
  }

  /**
   * Delete dealer.
   */
  @DeleteMapping(path = ID_PATH_VARIABLE)
  public ResponseEntity<Void> deleteDealer(final @PathVariable Long id) {
    worksheetProductService.clearUsage(id);
    worksheetDealerService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
