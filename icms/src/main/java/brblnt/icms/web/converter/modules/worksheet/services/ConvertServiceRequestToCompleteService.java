package brblnt.icms.web.converter.modules.worksheet.services;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteService;
import brblnt.icms.web.model.modules.worksheet.services.request.ServiceRequest;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Service Request to Complete Service.
 */
@Component
public class ConvertServiceRequestToCompleteService implements Converter<ServiceRequest, CompleteService> {
  @Override
  public CompleteService convert(@NonNull final ServiceRequest source) {
    return new CompleteService(
            null,
            source.getServiceName(),
            source.getCustomCode(),
            source.getPriceN(),
            source.getPriceB(),
            source.getVat()
    );
  }
}
