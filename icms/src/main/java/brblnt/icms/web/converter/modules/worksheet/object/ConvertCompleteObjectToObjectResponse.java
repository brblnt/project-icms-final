package brblnt.icms.web.converter.modules.worksheet.object;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteObject;
import brblnt.icms.web.model.modules.worksheet.object.response.ObjectResponse;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Complete Object to Object Response.
 */
@Component
public class ConvertCompleteObjectToObjectResponse implements Converter<CompleteObject, ObjectResponse> {
  @Override
  public ObjectResponse convert(@NonNull final CompleteObject source) {
    return new ObjectResponse(
            source.getId(),
            source.getCustomer(),
            source.getItemName(),
            source.getItemBrand(),
            source.getItemType(),
            source.getItemSerial(),
            source.getOther()
    );
  }
}
