package brblnt.icms.web.controller.modules.common.rest;

import static brblnt.icms.service.utilities.Utilities.*;

import java.util.List;

import brblnt.icms.service.modules.common.model.complete.CompleteAddress;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteCustomer;
import brblnt.icms.service.service.modules.common.CommonAddressService;
import brblnt.icms.web.model.modules.common.request.AddressRequest;
import brblnt.icms.web.model.modules.common.response.AddressResponse;
import brblnt.icms.web.model.modules.worksheet.product.request.ProductRequest;
import brblnt.icms.web.model.modules.worksheet.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Address Rest Controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = API + ADDRESS_URL)
public class CommonAddressRestController {

  private final CommonAddressService commonAddressService;
  private final Converter<CompleteAddress, AddressResponse> completeAddressAddressResponseConverter;

  /**
   * Get all address.
   */
  @GetMapping(path = ROOT_URL)
  public List<AddressResponse> getAddresses() {
    return commonAddressService.getAll().stream()
            .map(completeAddressAddressResponseConverter::convert)
            .toList();
  }

  /**
   * Get address by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public AddressResponse getAddress(final @PathVariable Long id) {
    return completeAddressAddressResponseConverter.convert(commonAddressService.getById(id));
  }

  /**
   * Create address.
   */
  @PostMapping
  public AddressResponse createAddress(final @RequestBody AddressRequest addressRequest) {
    commonAddressService.save(addressRequest, commonAddressService.createEmpty());
    return completeAddressAddressResponseConverter.convert(
            commonAddressService.create(addressRequest));
  }

  /**
   * Update address.
   */
  @PutMapping(path = ID_PATH_VARIABLE)
  public AddressResponse updateAddress(final @PathVariable Long id, final @RequestBody AddressRequest addressRequest) {
    commonAddressService.save(addressRequest, commonAddressService.getById(id));
    return completeAddressAddressResponseConverter.convert(commonAddressService.getById(id));
  }

  /**
   * Delete address.
   */
  @DeleteMapping(path = ID_PATH_VARIABLE)
  public ResponseEntity<Void> deleteAddress(final @PathVariable Long id) {
    commonAddressService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
