package brblnt.icms.web.converter.modules.worksheet.worksheet;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteWorksheet;
import brblnt.icms.web.model.modules.worksheet.worksheet.response.WorksheetResponse;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Complete Worksheet to Worksheet Response.
 */
@Component
public class ConvertCompleteWorksheetToWorksheetResponse implements Converter<CompleteWorksheet, WorksheetResponse> {
  @Override
  public WorksheetResponse convert(@NonNull final CompleteWorksheet source) {
    return new WorksheetResponse(
            source.getId(),
            source.getState(),
            source.getEngineerCode(),
            source.getCustomer(),
            source.getObject(),
            source.getFaults(),
            source.getServices(),
            source.getProducts(),
            source.getCreateDate(),
            source.getFinishDate(),
            source.getOther()
    );
  }
}
