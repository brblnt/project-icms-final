package brblnt.icms.web.converter.modules.common;

import brblnt.icms.service.modules.common.model.complete.CompleteAddress;
import brblnt.icms.web.model.modules.common.request.AddressRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert Address Request to Complete Address.
 */
@Component
public class ConvertAddressRequestToCompleteAddress implements Converter<AddressRequest, CompleteAddress> {

  @Override
  public CompleteAddress convert(AddressRequest source) {
    return new CompleteAddress(
            null,
            source.getPostalCode(),
            source.getCityName(),
            source.getAddress(),
            source.getOther()
    );
  }
}
