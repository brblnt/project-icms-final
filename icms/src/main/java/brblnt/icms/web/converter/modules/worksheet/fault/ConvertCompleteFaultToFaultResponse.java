package brblnt.icms.web.converter.modules.worksheet.fault;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteFault;
import brblnt.icms.web.model.modules.worksheet.fault.response.FaultResponse;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Complete Fault to Fault Response.
 */
@Component
public class ConvertCompleteFaultToFaultResponse implements Converter<CompleteFault, FaultResponse> {
  @Override
  public FaultResponse convert(@NonNull final CompleteFault source) {
    return new FaultResponse(
            source.getId(),
            source.getFaultName(),
            source.getFaultOther()
    );
  }
}
