package brblnt.icms.web.converter.modules.worksheet.services;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteService;
import brblnt.icms.web.model.modules.worksheet.services.response.ServiceResponse;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Complete Service to Service Response.
 */
@Component
public class ConvertCompleteServiceToServiceResponse implements Converter<CompleteService, ServiceResponse> {
  @Override
  public ServiceResponse convert(@NonNull final CompleteService source) {
    return new ServiceResponse(
            source.getId(),
            source.getServiceName(),
            source.getCustomCode(),
            source.getPriceN(),
            source.getPriceB(),
            source.getVat()
    );
  }
}
