package brblnt.icms.web.converter.modules.worksheet.object;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteObject;
import brblnt.icms.web.model.modules.worksheet.object.request.ObjectRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Object Request to Complete Object.
 */
@Component
public class ConvertObjectRequestToCompleteObject implements Converter<ObjectRequest, CompleteObject> {
  @Override
  public CompleteObject convert(ObjectRequest source) {
    return null;
  }
}
