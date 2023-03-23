package brblnt.icms.web.converter.modules.worksheet.fault;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteFault;
import brblnt.icms.web.model.modules.worksheet.fault.request.FaultRequest;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Fault Request to Complete Fault.
 */
@Component
public class ConvertFaultRequestToCompleteFault implements Converter<FaultRequest, CompleteFault> {
  @Override
  public CompleteFault convert(@NonNull final FaultRequest source) {
    return new CompleteFault(
            null,
            source.getFaultName(),
            source.getFaultOther());
  }
}
