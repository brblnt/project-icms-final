package brblnt.icms.web.converter.modules.common;

import brblnt.icms.service.modules.common.model.complete.CompleteAddress;
import brblnt.icms.web.model.modules.common.response.AddressResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert Complete Address to Address Response.
 */
@Component
public class ConvertCompleteAddressToAddressResponse implements Converter<CompleteAddress, AddressResponse> {

  @Override
  public AddressResponse convert(CompleteAddress source) {
    return new AddressResponse(
            source.getAddressID(),
            source.getPostalCode(),
            source.getCityName(),
            source.getAddress(),
            source.getOther());
  }
}
